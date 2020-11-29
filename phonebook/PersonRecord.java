package phonebook;

public class PersonRecord implements Comparable<PersonRecord> {
    final String name;
    final String lastName;

    public PersonRecord(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public static PersonRecord parsePersonRecord(String phoneRecordStr) {
        var personRecordItems = phoneRecordStr.split(" ");
        var lastName = personRecordItems.length == 2 ? personRecordItems[1] : "";
        return new PersonRecord(personRecordItems[0], lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof PersonRecord) {
            PersonRecord personRecord = (PersonRecord) obj;
            return personRecord.name.equalsIgnoreCase(this.name) &&
                    personRecord.lastName.equalsIgnoreCase(this.lastName);
        }

        return false;
    }


    @Override
    public int compareTo(PersonRecord personRecord) {
        var comparison = name.compareToIgnoreCase(personRecord.name);

        if (comparison == 0) {
            comparison += lastName.compareToIgnoreCase(personRecord.lastName);
        }
        return comparison;
    }
}
