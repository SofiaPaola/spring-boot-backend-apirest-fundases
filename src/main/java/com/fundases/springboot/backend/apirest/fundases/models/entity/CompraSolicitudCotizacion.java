package com.fundases.springboot.backend.apirest.fundases.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comp_solicitudes_cotizacion")
public class CompraSolicitudCotizacion implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_solicitud_cotizacion")
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario usuarios;
	
	@Temporal(TemporalType.DATE)
	private Date fecha_registro;
	
	// ---------------------------------------------------------------
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor")
	@JsonIgnoreProperties(value={"comprasolicitudcotizacion", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	private Proveedor proveedor;
	
	// ---------------------------------------------------------------
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parametro_evalu")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private CompraEvalProvParametros compraevalprovparametros;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch= FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "id_solicitud_cotizacion_det")
	private List<CompraSolicitudCotizacionDetalle> items;
	

	public CompraSolicitudCotizacion() {
		items = new ArrayList<>();
	}

	
	@PrePersist
	public void PrePersist() {
		
		this.fecha_registro = new Date();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Proveedor getProveedores() {
		return proveedor;
	}

	public void setProveedores(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	 


	public List<CompraSolicitudCotizacionDetalle> getItems() {
		return items;
	}


	public void setItems(List<CompraSolicitudCotizacionDetalle> items) {
		this.items = items;
	}




	private static final long serialVersionUID = 1L;

}
