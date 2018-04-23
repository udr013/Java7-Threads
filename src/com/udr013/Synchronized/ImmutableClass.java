package com.udr013.Synchronized;

import java.util.Date;

public class ImmutableClass {

    final class Birthdate{   // final class cannot be extended  // so no override  etc
        private final Date birth;

        public Birthdate(Date bod){  // value can only be set when object is instantiated
            birth = bod;
        }

        public Date getBirth() {
            return (Date)birth.clone(); // clone will return an Object so we need to cast when any modifications to the returned value won't change birth
        }

        public boolean isOlder(Date other){
            return other.before(birth);
        }
    }
}
