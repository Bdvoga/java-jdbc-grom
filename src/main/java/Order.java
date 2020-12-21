public class Order {
    private long customerId;
    private String companyName;
    private String contactName;
    private String contactTitle;
//    ADDRESS
//    CITY
//    REGION
//    POSTAL_CODE
//    COUNTRY
//    PHONE
//    FAX
//    IS_ACTIVE

    public Order(long customerId, String companyName, String contactName, String contactTitle) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId=" + customerId +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactTitle='" + contactTitle + '\'' +
                '}';
    }
}
