
CREATE SEQUENCE seq_hkp_crm_customer_apply_hid_or_pri
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE seq_hkp_crm_customer_apply_hid_or_pri
    OWNER TO hkp;

CREATE TABLE t_hkp_crm_customer_apply_hid_or_pri
(
    id   BIGINT NOT NULL DEFAULT nextval('seq_hkp_crm_customer_apply_hid_or_pri' :: REGCLASS),
    cus_apply_hop_code CHARACTER VARYING,
    owner_code        CHARACTER VARYING,
    type                 integer,
    check_code           CHARACTER VARYING,
    check_name           CHARACTER VARYING,
    status        integer,
    create_code   CHARACTER VARYING,
    cretae_name   CHARACTER VARYING,
    create_dept_code      CHARACTER VARYING,
    create_time      TIMESTAMP WITHOUT TIME ZONE,
    last_update_time TIMESTAMP WITHOUT TIME ZONE,
    is_deleted       BOOLEAN         DEFAULT FALSE,
    CONSTRAINT pk_hkp_crm_customer_apply_hid_or_pri PRIMARY KEY (id)
) WITH (OIDS = FALSE
    );
ALTER TABLE t_hkp_crm_customer_apply_hid_or_pri
    OWNER TO hkp;


COMMENT ON TABLE t_hkp_crm_customer_apply_hid_or_pri IS '客户申请隐藏或拉私表';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.cus_apply_hop_code IS '编码';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.owner_code IS '关联编码';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.type IS '类型（1.隐藏,2.拉私）';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.check_code IS '审核人编码';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.check_name IS '审核人姓名';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.status IS '状态（1.待审核2.通过3.不通过）';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.create_code IS '创建人';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.cretae_name IS '创建人姓名';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.create_dept_code IS '创建人部门';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.is_deleted IS '是否删除';


ALTER TABLE t_hkp_crm_customer_apply_hid_or_pri ADD COLUMN reason CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.reason IS '申请原因';

ALTER TABLE t_hkp_crm_customer_apply_hid_or_pri ADD COLUMN failure_reasons CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer_apply_hid_or_pri.failure_reasons IS '失败原因';

