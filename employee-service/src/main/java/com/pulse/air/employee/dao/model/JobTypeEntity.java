package com.pulse.air.employee.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the "ems_job_type" database table.
 * 
 */
@Data
@Entity
@Table(name = "ems_job_type")
@NamedQuery(name = "JobTypeEntity.findAll", query = "SELECT j FROM JobTypeEntity j")
public class JobTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EMS_JOB_TYPE_ID_GENERATOR", sequenceName = "EMS_JOB_TYPE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMS_JOB_TYPE_ID_GENERATOR")
	private Long id;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	private String responsibilities;

	@Column(name = "salary_max")
	private BigDecimal salaryMax;

	@Column(name = "salary_min")
	private BigDecimal salaryMin;

	private String status;

	private String title;

	// bi-directional many-to-one association to PositionEntity
	@OneToMany(mappedBy = "jobType")
	private List<PositionEntity> positions;

}