package app.enums;

public enum ColumnType {
    DECIMAL(0, "Long"),
    BIGINT(1, "Long"),
    DATETIME(2, "String"),
    VARCHAR(3, "String");

    private Integer id;
    private String objectType;

    ColumnType(Integer id, String objectType) {
        this.id = id;
        this.objectType = objectType;
    }

    public Integer getId() {
        return id;
    }

    public String getObjectType() {
        return objectType;
    }
}
