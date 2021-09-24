package com.example.demo.model.output;


public class ToppingOutput  {


    private String name;

    private Double price;

    private ToppingOutput() {
    }

    public static final class ToppingOutputBuilder {

        private String name;

        private Double price;

        public ToppingOutputBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ToppingOutputBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public ToppingOutput build() {
            ToppingOutput toppingOutput = new ToppingOutput();
            toppingOutput.setName(this.name);
            toppingOutput.setPrice(this.price);
            return toppingOutput;
        }

    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
