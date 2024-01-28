package com.eshop.eshop.entity;

import com.eshop.eshop.Utils.CloneUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Embeddable
@Setter
@Getter
public class AuditSection {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "UPDT_ID", length = 60)
    private String modifiedBy;

    public AuditSection() {}

    public Date getDateCreated() {
        return CloneUtils.clone(dateCreated);
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = CloneUtils.clone(dateCreated);
    }

    public Date getDateModified() {
        return CloneUtils.clone(dateModified);
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = CloneUtils.clone(dateModified);
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        if(!StringUtils.isBlank(modifiedBy)) {
            if(modifiedBy.length()>20) {
                modifiedBy = modifiedBy.substring(0, 20);
            }
        }
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return modifiedBy;
    }

    public void setCreatedBy(String modifiedBy) {
        if (!StringUtils.isBlank(modifiedBy)) {
            if (modifiedBy.length() > 20) {
                modifiedBy = modifiedBy.substring(0, 20);
            }
        }
        this.modifiedBy = modifiedBy;
    }
}
