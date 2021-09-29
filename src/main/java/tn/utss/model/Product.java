package tn.utss.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";

	@Id
	private long idProduct;

	@Field(value = "ProductTitle")
	private String titleProduct;
	@Field(value = "ProductDescription")
	private String descriptionProduct;
	@Field(value = "ProductQuantity")
	private int quantityProduct;
	@Indexed(direction = IndexDirection.ASCENDING)
	@Field(value = "ProductPrice")
	private float priceProduct;
	@Field(value = "ProductWeight")
	private float weightProduct;
	@Indexed(direction = IndexDirection.ASCENDING)
	@Field(value = "ProductBuyingPrice")
	private float buyingPriceProduct;
	@Field(value = "ProductMaxQuantity")
	private int maxQuantityProduct;
	@Field(value = "ProductImage")
	private String imageProd;
	@Field(value = "Barcodeprod")
	private String barcode;
	@Field(value = "fileName")
	private String fileName;
	public long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}
	public String getTitleProduct() {
		return titleProduct;
	}
	public void setTitleProduct(String titleProduct) {
		this.titleProduct = titleProduct;
	}
	public String getDescriptionProduct() {
		return descriptionProduct;
	}
	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}
	public int getQuantityProduct() {
		return quantityProduct;
	}
	public void setQuantityProduct(int quantityProduct) {
		this.quantityProduct = quantityProduct;
	}
	public float getPriceProduct() {
		return priceProduct;
	}
	public void setPriceProduct(float priceProduct) {
		this.priceProduct = priceProduct;
	}
	public float getWeightProduct() {
		return weightProduct;
	}
	public void setWeightProduct(float weightProduct) {
		this.weightProduct = weightProduct;
	}
	public float getBuyingPriceProduct() {
		return buyingPriceProduct;
	}
	public void setBuyingPriceProduct(float buyingPriceProduct) {
		this.buyingPriceProduct = buyingPriceProduct;
	}
	public int getMaxQuantityProduct() {
		return maxQuantityProduct;
	}
	public void setMaxQuantityProduct(int maxQuantityProduct) {
		this.maxQuantityProduct = maxQuantityProduct;
	}
	public String getImageProd() {
		return imageProd;
	}
	public void setImageProd(String imageProd) {
		this.imageProd = imageProd;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + Float.floatToIntBits(buyingPriceProduct);
		result = prime * result + ((descriptionProduct == null) ? 0 : descriptionProduct.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + (int) (idProduct ^ (idProduct >>> 32));
		result = prime * result + ((imageProd == null) ? 0 : imageProd.hashCode());
		result = prime * result + maxQuantityProduct;
		result = prime * result + Float.floatToIntBits(priceProduct);
		result = prime * result + quantityProduct;
		result = prime * result + ((titleProduct == null) ? 0 : titleProduct.hashCode());
		result = prime * result + Float.floatToIntBits(weightProduct);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (Float.floatToIntBits(buyingPriceProduct) != Float.floatToIntBits(other.buyingPriceProduct))
			return false;
		if (descriptionProduct == null) {
			if (other.descriptionProduct != null)
				return false;
		} else if (!descriptionProduct.equals(other.descriptionProduct))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (idProduct != other.idProduct)
			return false;
		if (imageProd == null) {
			if (other.imageProd != null)
				return false;
		} else if (!imageProd.equals(other.imageProd))
			return false;
		if (maxQuantityProduct != other.maxQuantityProduct)
			return false;
		if (Float.floatToIntBits(priceProduct) != Float.floatToIntBits(other.priceProduct))
			return false;
		if (quantityProduct != other.quantityProduct)
			return false;
		if (titleProduct == null) {
			if (other.titleProduct != null)
				return false;
		} else if (!titleProduct.equals(other.titleProduct))
			return false;
		if (Float.floatToIntBits(weightProduct) != Float.floatToIntBits(other.weightProduct))
			return false;
		return true;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String titleProduct, String descriptionProduct, int quantityProduct, float priceProduct,
			float weightProduct, float buyingPriceProduct, int maxQuantityProduct, String imageProd, String barcode,
			String fileName) {
		super();
		this.titleProduct = titleProduct;
		this.descriptionProduct = descriptionProduct;
		this.quantityProduct = quantityProduct;
		this.priceProduct = priceProduct;
		this.weightProduct = weightProduct;
		this.buyingPriceProduct = buyingPriceProduct;
		this.maxQuantityProduct = maxQuantityProduct;
		this.imageProd = imageProd;
		this.barcode = barcode;
		this.fileName = fileName;
	}
	public Product(long idProduct, String titleProduct, String descriptionProduct, int quantityProduct,
			float priceProduct, float weightProduct, float buyingPriceProduct, int maxQuantityProduct, String imageProd,
			String barcode, String fileName) {
		super();
		this.idProduct = idProduct;
		this.titleProduct = titleProduct;
		this.descriptionProduct = descriptionProduct;
		this.quantityProduct = quantityProduct;
		this.priceProduct = priceProduct;
		this.weightProduct = weightProduct;
		this.buyingPriceProduct = buyingPriceProduct;
		this.maxQuantityProduct = maxQuantityProduct;
		this.imageProd = imageProd;
		this.barcode = barcode;
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", titleProduct=" + titleProduct + ", descriptionProduct="
				+ descriptionProduct + ", quantityProduct=" + quantityProduct + ", priceProduct=" + priceProduct
				+ ", weightProduct=" + weightProduct + ", buyingPriceProduct=" + buyingPriceProduct
				+ ", maxQuantityProduct=" + maxQuantityProduct + ", imageProd=" + imageProd + ", barcode=" + barcode
				+ ", fileName=" + fileName + "]";
	}
	
	
	
	
	
	
	
}
