package jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization {
    private String orgNo;
    private String orgName;
    private String orglocation;
    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    private List<Department> departments;

    public Organization() {}

    public Organization(List<Department> departments, String orgNo, String orgName, String orglocation) {
        this.departments = departments;
        this.orgNo = orgNo;
        this.orgName = orgName;
        this.orglocation = orglocation;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
    public String getOrgNo() {
        return orgNo;
    }
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getOrglocation() {
        return orglocation;
    }
    public void setOrglocation(String orglocation) {
        this.orglocation = orglocation;
    }
}