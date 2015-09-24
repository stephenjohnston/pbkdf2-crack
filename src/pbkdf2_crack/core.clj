(ns pbkdf2-crack.core
  "A tool to recover a lost or forgotten restrictions password on iOS"
 (:require [crypto.password.pbkdf2 :as password]))

(defn- check-pbkdf2
  "Checks to see if a the pbkdf2 encrypted guess string matches the given 
   encrypted string.  Returns a map that contains the guess and a 
   true/false result."
  [guess hash]
  {:result (password/check guess hash) :value guess })

(defn- lazy-list-of-pbkdf2-checks
  "For each of the strings in the coll 'guesses', check it against the given
   encrypted hash string. Returns a lazy list of the pbkdf2 checks."
  [hash guesses]
  (map #(check-pbkdf2 % hash) guesses))

(defn- zero-padded-list
  "Given a list of integers, convert them to strings of length four, 
   left-padded with zeros if needed."
  [nums]
  (map #(format "%04d" %) nums))

(defn brute-force-cracker
  "Take a brute force approach to try to find the password between the values
   '0000' thru '9999'.  The 'hash' input is a string value of the form 
   <iterations>$<salt>$<encrypted password>, where the number of iterations and
   the salt are base64 encoded strings."
  [hash]
  (if (or (not (instance? String hash))
          (not (= (count (re-seq (re-pattern "\\$") hash)) 2)))
    (throw (IllegalArgumentException. "parameter hash must be a string of the form <base64iterations>$<base64salt>$<encryptedpassword>"))
    ;; Thread the numbers thru as the last argument to each of the functions
    ;; in the chain below. Note that first will ensure that we compute only up to
    ;; the first positive hit, and no more.
    (->> (range 1 10000) 
         (zero-padded-list)
         (lazy-list-of-pbkdf2-checks hash)
         (drop-while #(false? (:result %1)))
         (first)))) 

(defn -main
  "Using Leiningen, run lein run '<base64iterations>$<base64salt>$<encryptedpassword>'
   Example: lein run 'A+g=$aSbUXg==$M/p4734c8/SOXZnGgZot+BciAW0='"
  [& args]
  (if (not (= (count args) 1))
    (println "Usage: lein run '<base64iterations>$<base64salt>$<encryptedpassword>'")
    (println "Crack result: " (brute-force-cracker (first args)))))
