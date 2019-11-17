


----文本协议表----
CREATE SEQUENCE seq_hkp_base_textlegal
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_base_textlegal
  OWNER TO hkp;

CREATE TABLE t_hkp_base_textlegal
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_base_textlegal' :: REGCLASS),

  text_code         CHARACTER VARYING,
  text_legal         CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_base_textlegal PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_base_textlegal
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_base_textlegal IS '协议文本表';
COMMENT ON COLUMN t_hkp_base_textlegal.text_code IS '协议文本编码';
COMMENT ON COLUMN t_hkp_base_textlegal.remark IS '跟进订单内容';
COMMENT ON COLUMN t_hkp_base_textlegal.cpy_code IS '所属公司编码';

