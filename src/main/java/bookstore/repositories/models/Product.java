package bookstore.repositories.models;

import framework.core.Storage.Storable;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public abstract class Product implements Storable<String> {
    String id;
    String name;
	double price;
	@Override
	public String getStorageKey() {
		return this.id;
	}
/*
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
*/

}
