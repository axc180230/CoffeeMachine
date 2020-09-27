// declare your enum here
    enum Currency {
        USD("United States dollar"),
        EUR("Euro"),
        GBP("Pound sterling"),
        RUB("Russian ruble"),
        UAH("Ukrainian, hyrvnia"),
        KZT("Kazakhstani tenge"),
        CAD("Canadian dollar"),
        JPY("Japanese yen"),
        CNY("Chinese yuan");

        String currency;

        Currency(String currnency) {
            this.currency = currnency;
        }
    }

