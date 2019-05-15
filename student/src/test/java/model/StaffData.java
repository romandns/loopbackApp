package model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Objects;

@XStreamAlias("staff")
public class StaffData {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String staffPosition;
    @Expose
    private String starShip;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public String getStarShip() {
        return starShip;
    }


    public StaffData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StaffData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StaffData withStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
        return this;
    }

    public StaffData withStarship(String starShip) {
        this.starShip = starShip;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffData staffData = (StaffData) o;
        return Objects.equals(firstName, staffData.firstName) &&
                Objects.equals(lastName, staffData.lastName) &&
                Objects.equals(staffPosition, staffData.staffPosition) &&
                Objects.equals(starShip, staffData.starShip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, staffPosition, starShip);
    }


    @Override
    public String toString() {
        String str = "{" +
                "\"firstName\":\"" + firstName + "\"," +
                "\"lastName\":\""+ lastName + "\"," +
                "\"staffPosition\":\""+ staffPosition + "\","+
                "\"starShip\":\""+ starShip + "\""+
                "}";
        return str;
    }
}
