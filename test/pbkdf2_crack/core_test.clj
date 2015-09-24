(ns pbkdf2-crack.core-test
  (:require [clojure.test :refer :all]
            [pbkdf2-crack.core :refer :all]))

(deftest basic-test-for-0001
  (testing "Test to see that 0001 passes"
    (is (= "0001" (brute-force-cracker "A+g=$aSbUXg==$M/p4734c8/SOXZnGgZot+BciAW0=")))))

(deftest basic-test-for-0128
  (testing "Test to see that 0128 passes"
    (is (= "0128" (brute-force-cracker "AYag$k66pDaGZf24=$OgwkGYtjuG+r7zNMzGyzxTL8MeE=")))))

(deftest test-with-unknown-key
  (testing "Test to see that a passcode outside the range of 0000 to 9999 results in nil"
    (is (nil? (brute-force-cracker "A+g=$k66pDaGZf24=$xxxkGYtjuG+r7zNMzGyzxTL8MeE=")))))

(deftest test-with-complete-and-total-garbage
  (testing "Test with complete and total garbage.  Some random string as input."
    (is (thrown? IllegalArgumentException (brute-force-cracker "ThisIsCompleteAndTotalGarbage!!@#%!#%@")))))

(deftest test-with-complete-and-total-garbage-2
  (testing "Test with complete and total garbage. a random hex number instead of a string"
    (is (thrown? IllegalArgumentException (brute-force-cracker 0xCAFEBABE)))))
