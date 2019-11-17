----集中获客表----
CREATE SEQUENCE seq_hkp_crm_focus_cus
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_focus_cus
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_focus_cus
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_crm_focus_cus' :: REGCLASS),
  cus_code        CHARACTER VARYING,
  user_code        CHARACTER VARYING,
  handle_status    INT,
  has_handle      BOOLEAN DEFAULT FALSE,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_focus_cus PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_focus_cus
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_focus_cus IS '集中获客表';
COMMENT ON COLUMN t_hkp_crm_focus_cus.cus_code IS '集中获客的客户编码';
COMMENT ON COLUMN t_hkp_crm_focus_cus.user_code IS '集中获客的用户编码';
COMMENT ON COLUMN t_hkp_crm_focus_cus.handle_status IS '处理状态';
COMMENT ON COLUMN t_hkp_crm_focus_cus.has_handle IS '是否处理';
COMMENT ON COLUMN t_hkp_crm_focus_cus.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_crm_focus_cus.last_update_code IS '最新更新人编码';
COMMENT ON COLUMN t_hkp_crm_focus_cus.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_crm_focus_cus.last_update_time IS '最新更进时间';
COMMENT ON COLUMN t_hkp_crm_focus_cus.is_deleted IS '是否删除';

ALTER TABLE t_hkp_crm_focus_cus ADD COLUMN focus_type  CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_focus_cus.focus_type IS '集中获客类型';

ALTER TABLE t_hkp_crm_focus_cus ADD COLUMN user_dept_code  CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_focus_cus.user_dept_code IS '用户部门编码';
