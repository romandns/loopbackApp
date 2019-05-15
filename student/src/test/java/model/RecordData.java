package model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Arrays;
import java.util.Objects;

@XStreamAlias("record")
public class RecordData {
    @Expose
    private Integer recordId;
    @Expose
    private String recordLabel;
    @Expose
    private String relationType;
    @Expose
    private String recordCreationData;
    @Expose
    private String[] recordRelation;
    @Expose
    private String recordOwner;
    @Expose
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

    public String getRelationType() {
        return relationType;
    }

    public RecordData withRecordId(Integer recordId) {
        this.recordId = recordId;
        return this;
    }

    public RecordData withRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
        return this;
    }

    public RecordData withRelationType(String relationType) {
        this.relationType = relationType;
        return this;
    }

    public RecordData withRecordCreationData(String recordCreationData) {
        this.recordCreationData = recordCreationData;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordData that = (RecordData) o;
        return Objects.equals(recordId, that.recordId) &&
                Objects.equals(recordLabel, that.recordLabel) &&
                Objects.equals(relationType, that.relationType) &&
                Objects.equals(recordCreationData, that.recordCreationData) &&
                Arrays.equals(recordRelation, that.recordRelation) &&
                Objects.equals(recordOwner, that.recordOwner) &&
                Objects.equals(recordStatus, that.recordStatus);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(recordId, recordLabel, relationType, recordCreationData, recordOwner, recordStatus);
        result = 31 * result + Arrays.hashCode(recordRelation);
        return result;
    }

    public RecordData withRecordRelation(String[] recordRelation) {
        this.recordRelation = recordRelation;
        return this;
    }

    public RecordData withRecordOwner(String recordOwner) {
        this.recordOwner = recordOwner;
        return this;
    }

    public RecordData withRecordStatus(Boolean recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    @Override
    public String toString() {
        String str = "\n{" +
                "\"recordId\":" + recordId + "," +
                "\n\"recordLabel\":\"" + recordLabel + "\"," +
                "\n\"recordCreationData\":\"" + recordCreationData + "\"," +
                "\n\"recordRelation\":" +
                "[ {" +
                "\n\"relationType\":\"" + recordRelation + "\"" +
                "\n\"relationType\":\"" + relationType + "\"" +
                "} ]," +
                "\n\"recordOwner\":\"" + recordOwner +  "\"," +
                "\n\"recordStatus\":" + recordStatus +
                "}";
        return str;
    }


}
