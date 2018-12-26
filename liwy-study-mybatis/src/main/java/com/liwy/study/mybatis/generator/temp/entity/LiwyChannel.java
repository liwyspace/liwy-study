package com.liwy.study.mybatis.generator.temp.entity;

public class LiwyChannel {
    /**
     * 栏目ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 上级栏目ID
     *
     * @mbg.generated
     */
    private Integer parentId;

    /**
     * 排列顺序
     *
     * @mbg.generated
     */
    private Integer priority;

    /**
     * 栏目名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 是否在新窗口打开
     *
     * @mbg.generated
     */
    private Boolean isBlank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsBlank() {
        return isBlank;
    }

    public void setIsBlank(Boolean isBlank) {
        this.isBlank = isBlank;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", priority=").append(priority);
        sb.append(", name=").append(name);
        sb.append(", isBlank=").append(isBlank);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LiwyChannel other = (LiwyChannel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIsBlank() == null ? other.getIsBlank() == null : this.getIsBlank().equals(other.getIsBlank()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIsBlank() == null) ? 0 : getIsBlank().hashCode());
        return result;
    }
}