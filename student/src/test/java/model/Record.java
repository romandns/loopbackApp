package model;

public class Record {

    private Integer recordId;
    private String recordLabel;
    private String recordCreationData;
    private String[] recordRelation;
    private String recordOwner;
    private Boolean recordStatus;

    public Integer getRecordId() {
        return recordId;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public String getRecordCreationData() {
        return recordCreationData;
    }

    public String[] getRecordRelation() {
        return recordRelation;
    }

    public String getRecordOwner() {
        return recordOwner;
    }

    public Boolean getRecordStatus() {
        return recordStatus;
    }

    public Record setRecordId(Integer recordId) {
        this.recordId = recordId;
        return this;
    }

    public Record setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
        return this;
    }

    public Record setRecordCreationData(String recordCreationData) {
        this.recordCreationData = recordCreationData;
        return this;
    }

    public Record setRecordRelation(String[] recordRelation) {
        this.recordRelation = recordRelation;
        return this;
    }

    public Record setRecordOwner(String recordOwner) {
        this.recordOwner = recordOwner;
        return this;
    }

    public Record setRecordStatus(Boolean recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }
}
