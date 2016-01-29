package br.com.comparecell.entidades;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@Table(catalog = "comparecell", name = "aparelho")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "src/br/com/comparecell/entidades", name = "Aparelho")
@XmlRootElement(namespace = "src/br/com/comparecell/entidades")
public class Aparelho implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	Integer id;
	/**
	 */

	@Column(name = "MODELO", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String modelo;
	/**
	 */
	
	@Column(name = "LANCAMENTO", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String lancamento;
	/**
	 */

	@Column(name = "STATUS", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean status;
	/**
	 */

	@Column(name = "CPU_CORE", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	int cpuCore;
	/**
	 */

	@Column(name = "CPU_FREQUENCA", precision = 12, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal cpuFrequenca;
	/**
	 */

	@Column(name = "CPU_DESCRICAO", length = 5, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String cpuDescricao;
	/**
	 */

	@Column(name = "ARMAZENAMENTO_INTERNO", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer armazenamentoInterno;
	/**
	 */

	@Column(name = "ARMAZENAMENTO_CARTAO", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer armazenamentoCartao;
	/**
	 */

	@Column(name = "MEMORIA", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer memoria;
	/**
	 */

	@Column(name = "MEMORIA_DESC", length = 4, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String memoriaDesc;
	/**
	 */

	@Column(name = "DISPLAY_RESOLUCAO", precision = 12, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String displayResolucao;
	/**
	 */

	@Column(name = "DISPLAY_TAMANHO", precision = 12, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Float displayTamanho;
	/**
	 */

	@Column(name = "CAMERA_PRIMARIA", precision = 12, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Float cameraPrimaria;
	/**
	 */

	@Column(name = "CAMERA_SECUNDARIA", precision = 12, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Float cameraSecundaria;
	/**
	 */

	@Column(name = "IMG_CELULAR", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String imgCelular;

	/**
	 */
	@OneToMany(mappedBy = "aparelho", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<br.com.comparecell.entidades.Rank> ranks;

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 */
	public String getModelo() {
		return this.modelo;
	}

	/**
	 */
	public void setLancamento(String lancamento) {
		this.lancamento = lancamento;
	}

	/**
	 */
	public String getLancamento() {
		return this.lancamento;
	}

	/**
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 */
	public Boolean getStatus() {
		return this.status;
	}

	/**
	 */
	public void setCpuCore(int cpuCore) {
		this.cpuCore = cpuCore;
	}

	/**
	 */
	public int getCpuCore() {
		return this.cpuCore;
	}

	/**
	 */
	public void setCpuFrequenca(BigDecimal cpuFrequenca) {
		this.cpuFrequenca = cpuFrequenca;
	}

	/**
	 */
	public BigDecimal getCpuFrequenca() {
		return this.cpuFrequenca;
	}

	/**
	 */
	public void setCpuDescricao(String cpuDescricao) {
		this.cpuDescricao = cpuDescricao;
	}

	/**
	 */
	public String getCpuDescricao() {
		return this.cpuDescricao;
	}

	/**
	 */
	public void setArmazenamentoInterno(Integer armazenamentoInterno) {
		this.armazenamentoInterno = armazenamentoInterno;
	}

	/**
	 */
	public Integer getArmazenamentoInterno() {
		return this.armazenamentoInterno;
	}

	/**
	 */
	public void setArmazenamentoCartao(Integer armazenamentoCartao) {
		this.armazenamentoCartao = armazenamentoCartao;
	}

	/**
	 */
	public Integer getArmazenamentoCartao() {
		return this.armazenamentoCartao;
	}

	/**
	 */
	public void setMemoria(Integer memoria) {
		this.memoria = memoria;
	}

	/**
	 */
	public Integer getMemoria() {
		return this.memoria;
	}

	/**
	 */
	public void setMemoriaDesc(String memoriaDesc) {
		this.memoriaDesc = memoriaDesc;
	}

	/**
	 */
	public String getMemoriaDesc() {
		return this.memoriaDesc;
	}

	/**
	 */
	public void setDisplayResolucao(String displayResolucao) {
		this.displayResolucao = displayResolucao;
	}

	/**
	 */
	public String getDisplayResolucao() {
		return this.displayResolucao;
	}

	/**
	 */
	public void setDisplayTamanho(Float displayTamanho) {
		this.displayTamanho = displayTamanho;
	}

	/**
	 */
	public Float getDisplayTamanho() {
		return this.displayTamanho;
	}

	/**
	 */
	public void setCameraPrimaria(Float cameraPrimaria) {
		this.cameraPrimaria = cameraPrimaria;
	}

	/**
	 */
	public Float getCameraPrimaria() {
		return this.cameraPrimaria;
	}

	/**
	 */
	public void setCameraSecundaria(Float cameraSecundaria) {
		this.cameraSecundaria = cameraSecundaria;
	}

	/**
	 */
	public Float getCameraSecundaria() {
		return this.cameraSecundaria;
	}

	/**
	 */
	public void setImgCelular(String imgCelular) {
		this.imgCelular = imgCelular;
	}

	/**
	 */
	public String getImgCelular() {
		return this.imgCelular;
	}

	/**
	 */
	public void setRanks(Set<Rank> ranks) {
		this.ranks = ranks;
	}

	/**
	 */
	public Set<Rank> getRanks() {
		if (ranks == null) {
			ranks = new java.util.LinkedHashSet<br.com.comparecell.entidades.Rank>();
		}
		return ranks;
	}

	
	
}
