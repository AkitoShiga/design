class ShippingCost {
        static final int minumumForFree = 3000;
        static final int int cost       = 500;

        int basePrice; // クライアント側の引数をプロパティにする

        ShippingCost(int basePrice) {
            this.basePrice = basePrice;
        }

        int amount() {
            if(basePrice < minimumForFree) return cost;
            return 0;
        }
}

/* クライアント側 */
int shippingCost(int basePrice) {
    // 軽率にインスタンス化していいんだ
    ShippingCost cost = new ShippingCost(basePrie);
        return cost.amount();
    }
}

class Quantity {
    static final int MIN = 1;
    static final int MAX = 100;

    int value;

    Quantity(int value) {
        // 
        if(value < MIN) throw new IllegalArgumentException("不正" + MIN + "未満");
        if(value > MAX) throw new IllegalArgumentException("不正" + MAX + "超");
        this.value = value;
    }

    boolean canAdd(Quantity other) {
        int added = addValue(other);
        return added <= MAX;
    }

    Quantity add(Quantity other) {
        if(!canAdd(other)) throw new
            IllegalArgumentException("不正：合計が" + MAX + "超");
        int added = addValue(other);
        return new Quantitiy(added);

    }

    private int addValue(Quantity other) {
        return this.value + other.value;
    }
}
int amount(int unitPirice, int quantity) {
    if(quantity >= discountCriteria)
        return discountAmount(unitPrice, quantity)
    return unitPrice * quantity;
}

Moneyamount(Money unitPrice, Quantity quantity) {
    if(quantity.isDiscountable()) return discount(unitPrice, quantity)

        return unitPrice.multiply(quantity.value());
}
