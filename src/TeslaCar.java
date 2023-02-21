package src;

import java.math.BigInteger;

import java.time.Year;
import java.time.YearMonth;

public class TeslaCar {
    private YearMonth date;
    private TeslaModel model;
    private Integer sold;

    public TeslaCar(YearMonth date, TeslaModel model, Integer sold) {
        this.date = date;
        this.model = model;
        this.sold = sold;
    }

    public YearMonth getDate() {
        return this.date;
    }

    public void setDate(YearMonth date) {
        this.date = date;
    }

    public TeslaModel getModel() {
        return this.model;
    }

    public void setModel(String modelName) {
        this.model.setModelName(modelName);
    }

    public Integer getSold() {
        return this.sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }
}
