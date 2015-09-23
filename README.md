# pbkdf2-crack

This library is a clojure port of Seafoodbuffet's [iOSRestrictionsExtractor](https://github.com/seafoodbuffet/iOSRestrictionsExtractor) used to recover an iOS restrictions passcode. More details can be found on [ios-recover-restrictions-passcode](http://blog.barelycode.com//2015/09/22/ios-recover-restrictions-passcode/)

This project was just for fun to see what the clojure equivalent might look like and to see how fast it runs.
To recover '0001', it takes about 80 milliseconds, and about 20 to 30 seconds to recover '9999'.

This implementation was made simple by using [weavejester's](https://github.com/weavejester) [crypto-password](https://github.com/weavejester/crypto-password).

## Usage

    $ lein run 'A+g=$aSbUXg==$M/p4734c8/SOXZnGgZot+BciAW0='
    Crack result: {:result true, :value "0001"}
## License

Copyright © 2015 

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
