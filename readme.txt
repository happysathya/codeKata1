Assume you have a List of account numbers with the following values. All of them are string length 9.

457508000
664371495
86110??36
345882865
490067715
711111111
123456789
490867715
88888?888
012345678
666?66666

Task:

* Process all the account numbers and store them in a Map along with its status.

    How to calculate the status:

        * If the Account number has characters other than number, then store the status as "ILL".

        * Calculate the status using the following logic.

            account number:  3  4  5  8  8  2  8  6  5
            position names:  d9 d8 d7 d6 d5 d4 d3 d2 d1

            checksum calculation:
            ((1*d1) + (2*d2) + (3*d3) + ... + (9*d9)) mod 11 == 0

            * If the result is 0, then store the status as "VAL"
            * If the the result is non-zero, then store the status as "ERR"

    * Write another function, where it takes the Map.
        If the status is "ILL" and the account number has a single character error, replace the illegal character with
        any digit between 0 and 9 and find the valid account number and print it.