package Problems;
public class Student implements Comparable<Student> {
    private int numberId;
    private String name;
    private String birthDate;
    private String homeAddress;
    private int classId;
    private String enrollmentDate;
    private String status;

    public Student(int numberId, String name, String birthDate, String homeAddress, int classId, String enrollmentDate,
            String status) {
        this.numberId = numberId;
        this.name = name;
        this.birthDate = birthDate;
        this.homeAddress = homeAddress;
        this.classId = classId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public int getNumberId() {
        return this.numberId;
    }

    public void setNumberId(int numberId) {
        this.numberId = numberId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHomeAddress() {
        return this.homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public int getClassId() {
        return this.classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
            " numberId='" + getNumberId() + "'" +
            ", name='" + getName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", homeAddress='" + getHomeAddress() + "'" +
            ", classId='" + getClassId() + "'" +
            ", enrollmentDate='" + getEnrollmentDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(numberId, o.numberId);
    }

}
