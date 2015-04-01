/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boot_Pack;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luiz Angel
 */
@Entity
@Table(name = "TABLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tables.findAll", query = "SELECT t FROM Tables t"),
    @NamedQuery(name = "Tables.findByHyperlink", query = "SELECT t FROM Tables t WHERE t.hyperlink = :hyperlink"),
    @NamedQuery(name = "Tables.findByName", query = "SELECT t FROM Tables t WHERE t.name = :name"),
    @NamedQuery(name = "Tables.findByMetaTag", query = "SELECT t FROM Tables t WHERE t.metaTag = :metaTag"),
    @NamedQuery(name = "Tables.findByComment", query = "SELECT t FROM Tables t WHERE t.comment = :comment"),
    @NamedQuery(name = "Tables.findByDateCr", query = "SELECT t FROM Tables t WHERE t.dateCr = :dateCr"),
    @NamedQuery(name = "Tables.findByDateMod", query = "SELECT t FROM Tables t WHERE t.dateMod = :dateMod")})
public class Tables implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HYPERLINK")
    private String hyperlink;
    @Column(name = "NAME")
    private String name;
    @Column(name = "META_TAG")
    private String metaTag;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "DATE_CR")
    private String dateCr;
    @Column(name = "DATE_MOD")
    private String dateMod;

    public Tables() {
    }

    public Tables(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetaTag() {
        return metaTag;
    }

    public void setMetaTag(String metaTag) {
        this.metaTag = metaTag;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateCr() {
        return dateCr;
    }

    public void setDateCr(String dateCr) {
        this.dateCr = dateCr;
    }

    public String getDateMod() {
        return dateMod;
    }

    public void setDateMod(String dateMod) {
        this.dateMod = dateMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hyperlink != null ? hyperlink.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tables)) {
            return false;
        }
        Tables other = (Tables) object;
        if ((this.hyperlink == null && other.hyperlink != null) || (this.hyperlink != null && !this.hyperlink.equals(other.hyperlink))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Boot_Pack.Tables[ hyperlink=" + hyperlink + " ]";
    }
    
}
