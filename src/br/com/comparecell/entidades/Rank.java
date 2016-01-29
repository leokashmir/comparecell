package br.com.comparecell.entidades;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllRanks", query = "select myRank from Rank myRank"),
		@NamedQuery(name = "findRankByIdRank", query = "select myRank from Rank myRank where myRank.idRank = ?1"),
		@NamedQuery(name = "findRankByPrimaryKey", query = "select myRank from Rank myRank where myRank.idRank = ?1"),
		@NamedQuery(name = "findRankByRankField", query = "select myRank from Rank myRank where myRank.rankField = ?1") })
@Table(catalog = "comparecell", name = "rank")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "src/br/com/comparecell/entidades", name = "Rank")
public class Rank implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "ID_RANK", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	Integer idRank;
	/**
	 */

	@Column(name = "RANK", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer rankField;

	/**
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "FK_ID_CELULAR", referencedColumnName = "ID", nullable = false) })
	@XmlTransient
	Aparelho aparelho;

	/**
	 */
	public void setIdRank(Integer idRank) {
		this.idRank = idRank;
	}

	/**
	 */
	public Integer getIdRank() {
		return this.idRank;
	}

	/**
	 */
	public void setRankField(Integer rankField) {
		this.rankField = rankField;
	}

	/**
	 */
	public Integer getRankField() {
		return this.rankField;
	}

	/**
	 */
	public void setAparelho(Aparelho aparelho) {
		this.aparelho = aparelho;
	}

	/**
	 */
	public Aparelho getAparelho() {
		return aparelho;
	}

	
}
