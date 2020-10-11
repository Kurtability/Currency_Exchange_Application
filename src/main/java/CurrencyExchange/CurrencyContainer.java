package CurrencyExchange;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class CurrencyContainer {
    public final SimpleStringProperty name;
    public final SimpleStringProperty rate1;
    public final SimpleStringProperty rate2;
    public final SimpleStringProperty rate3;
    public final SimpleStringProperty rate4;

    public CurrencyContainer(String name, String rate1, String rate2, String rate3, String rate4) {
        this.name = new SimpleStringProperty(name);
        this.rate1 = new SimpleStringProperty(rate1);
        this.rate2 = new SimpleStringProperty(rate2);
        this.rate3 = new SimpleStringProperty(rate3);
        this.rate4 = new SimpleStringProperty(rate4);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name){
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        SimpleStringProperty n = new SimpleStringProperty("1 " + getName());
        return n;
    }

    public String getRate1() {
        return rate1.get();
    }
    public void setrate1(double rate1) {
        this.rate1.set(Double.toString(rate1));
    }
    public StringProperty rate1Property(){return this.rate1;
    }
    public String getrate2() {
        return rate2.get();
    }
    public void setrate2(double rate2) {
        this.rate2.set(Double.toString(rate2));
    }
    public StringProperty rate2Property(){return this.rate2;
    }

    public String getrate3() {
        return rate3.get();
    }
    public void setrate3(double rate3) {
        this.rate3.set(Double.toString(rate3));
    }
    public StringProperty rate3Property(){return this.rate3;
    }

    public String getrate4() {
        return rate4.get();
    }
    public void setrate4(double rate4) {
        this.rate4.set(Double.toString(rate4));
    }
    public StringProperty rate4Property(){return this.rate4;
    }
}
