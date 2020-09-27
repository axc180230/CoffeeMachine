class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        // implement me
        if (this.minutes == 59) {
            if (this.hours == 12) {
                this.hours = 1;
            } else {
                this.hours++;
            }
            this.minutes = 0;
        } else {
            this.minutes++;
        }
    }
}