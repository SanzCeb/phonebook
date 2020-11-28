package phonebook;

public class PhoneRecord implements Comparable<PhoneRecord>{
    private final PersonRecord personRecord;
    String phone;

    public PhoneRecord(PersonRecord personRecord, String phone) {
        this.personRecord = personRecord;
        this.phone = phone;
    }

    public PhoneRecord(PersonRecord personRecord) {
        this(personRecord, "");
    }

    public static PhoneRecord parsePhoneRecord (String phoneRecordStr) {
        var phoneRecordItems = phoneRecordStr.split(" ");
        var phone = phoneRecordItems[0];
        var firstName = (phoneRecordItems.length >= 2) ? phoneRecordItems[1] : "";
        var lastName = (phoneRecordItems.length == 3) ? phoneRecordItems[2] : "";

        return new PhoneRecord(new PersonRecord(firstName, lastName), phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof PhoneRecord) {
            PhoneRecord phoneR = (PhoneRecord) obj;
            return  this.personRecord.equals(phoneR.personRecord) &&
                    phone.equals(phoneR.phone);
        }

        return false;
    }

    @Override
    public int compareTo(PhoneRecord o) {
        return personRecord.compareTo(o.personRecord);
    }
}
