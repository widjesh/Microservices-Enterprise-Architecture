package models;

/**
 * The enum E product category.
 */
public enum EProductCategory {

    /**
     * The Smart phones.
     */
    SMART_PHONES("Smart phones"),

    /**
     * Tablets e product category.
     */
    TABLETS("Tablets"),

    /**
     * Books e product category.
     */
    BOOKS("Books"),

    /**
     * Clothes e product category.
     */
    CLOTHES("Clothes");

    private String description;

    EProductCategory(String description) {
        this.description = description;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
