(ns pbkdf2-crack.core-test
  (:require [clojure.test :refer :all]
            [pbkdf2-crack.core :refer :all]))

(deftest basic-test-for-0001
  (testing "Test to see that 0001 passes"
    (is (= "0001" (:value (brute-force-cracker "A+g=$aSbUXg==$M/p4734c8/SOXZnGgZot+BciAW0="))))))


(deftest basic-test-for-0128
  (testing "Test to see that 0128 passes"
    (is (= "0128" (:value (brute-force-cracker "AYag$k66pDaGZf24=$OgwkGYtjuG+r7zNMzGyzxTL8MeE="))))))


(deftest test-with-unknown-key
  (testing "Test to see that a passcode outside the range of 0000 to 9999 results in an empty list"
    (is (empty? (brute-force-cracker "A+g=$k66pDaGZf24=$xxxkGYtjuG+r7zNMzGyzxTL8MeE=")))))
