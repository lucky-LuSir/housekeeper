/*容联云电话接通记录*/
CREATE SEQUENCE seq_hkp_ronglian_phone_note INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

ALTER TABLE seq_hkp_ronglian_phone_note
    OWNER TO hkp;

CREATE TABLE t_hkp_ronglian_phone_note
(
    ID                BIGINT DEFAULT nextval(
            'seq_hkp_ronglian_phone_note' :: regclass
        ) NOT NULL
        CONSTRAINT pk_hkp_ronglian_phone_note PRIMARY KEY,
    call_no           VARCHAR,
    called_no         VARCHAR,
    ref_call_sheet_id VARCHAR,
    call_sheet_id     VARCHAR,
    call_id           VARCHAR,
    call_type         VARCHAR,
    ring              TIMESTAMP,
    ringing_date      TIMESTAMP,
    ringing_timestamp TIMESTAMP,
    "begin"           TIMESTAMP,
    "end"             TIMESTAMP,
    queue_time        TIMESTAMP,
    agent             VARCHAR,
    queue             VARCHAR,
    "state"           VARCHAR,
    call_state        VARCHAR,
    province          VARCHAR,
    district          VARCHAR,
    ivrkey            VARCHAR
);

COMMENT ON TABLE t_hkp_ronglian_phone_note IS '容联云电销通话接通记录';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.call_no IS '主叫';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.called_no IS '被叫';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.ref_call_sheet_id IS '转接前通话的CallSheetID';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.call_sheet_id IS '通话记录ID';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.call_id IS '通话ID';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.call_type IS '通话类型：dialout外呼通话,normal普通来电,transfer呼入转接,dialTransfer外呼转接';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.ring IS '通话振铃时间';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.ringing_date IS '被叫振铃开始时间';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.ringing_timestamp IS '被叫振铃开始时间（呼入是按座席振铃的时间,外呼按客户振铃的时间）';

COMMENT ON COLUMN t_hkp_ronglian_phone_note."begin" IS '通话接通时间（双方开始通话的时间,如果被叫没接听的话为空';

COMMENT ON COLUMN t_hkp_ronglian_phone_note."end" IS '通话结束时间';

COMMENT ON COLUMN t_hkp_ronglian_phone_note."queue_time" IS '来电进入技能组时间';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.agent IS '处理坐席的工号';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.queue IS '通话进入的技能组名称';

COMMENT ON COLUMN t_hkp_ronglian_phone_note."state" IS '接听状态：dealing（已接）,notDeal（振铃未接听）,leak（ivr放弃）,queueLeak（排队放弃）,blackList（黑名单）,voicemail（留言）,limit（并发限制）';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.call_state IS '事件状态：Ring, Ringing, Link, Hangup(Unlink也当成Hangup处理';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.province IS '目标号码的省';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.district IS '目标号码的市';

COMMENT ON COLUMN t_hkp_ronglian_phone_note.ivrkey IS '通话在系统中选择的按键菜单,10004@0。格式为：按键菜单的节点编号@选择的菜单按键。如果为多级菜单则为：10004@0-10005@1。';

ALTER TABLE t_hkp_ronglian_phone_note
    OWNER TO hkp;


/*容联云小号话单表*/
CREATE SEQUENCE seq_hkp_ronglian_little_phone_note INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

ALTER TABLE seq_hkp_ronglian_little_phone_note
    OWNER TO hkp;

CREATE TABLE t_hkp_ronglian_little_phone_note
(
    ID                BIGINT DEFAULT nextval(
            'seq_hkp_ronglian_little_phone_note' :: regclass
        ) NOT NULL
        CONSTRAINT pk_hkp_ronglian_little_phone_note PRIMARY KEY,
    alerting_time     VARCHAR,
    "called"          VARCHAR,
    recorder_id       VARCHAR,
    caller_area       VARCHAR,
    begin_time        TIMESTAMP,
    called_area       VARCHAR,
    caller            VARCHAR,
    called_show       VARCHAR,
    connect_time      TIMESTAMP,
    "result"          VARCHAR,
    "release_time"    VARCHAR,
    account           VARCHAR,
    mapping_id        VARCHAR,
    app_id            VARCHAR,
    "call_duration"   VARCHAR,
    user_data         VARCHAR,
    answer_time       TIMESTAMP,
    small_number_type VARCHAR,
    calldisplay       VARCHAR,
    record_file_url   VARCHAR
);

COMMENT ON TABLE t_hkp_ronglian_little_phone_note IS '容联云电销小号话单记录';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.alerting_time IS '被叫振铃时间,格式为YYYY-MM-DD HH:mm:ss';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note."called" IS '被叫真实号码';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.recorder_id IS '企业本次通话唯一标识id';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.caller_area IS '主叫归属地';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.begin_time IS '主叫拨通虚拟号码时刻 ，格式为YYYY-MM-DD HH:mm:ss';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.called_area IS '被叫归属地';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.caller IS '主叫号码';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.called_show IS '中间号,小号';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.connect_time IS '被叫接通时刻,格式为YYYY-MM-DD HH:mm:ss';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note."result" IS '通话状态，通话状态的取值请查通话状态区分（0 成功 2无应答 9呼叫失败 11主叫号码与中间号没有关联';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note."release_time" IS '通话结束时刻,格式为YYYY-MM-DD HH:mm:ss';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note."account" IS '帐号编号';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.mapping_id IS '绑定关系唯一Id';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.app_id IS '应用id';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note."call_duration" IS '本次通话的时长，单位为秒';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.user_data IS '用户自定义数据';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.answer_time IS '接通时间';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.small_number_type IS '小号类型';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.calldisplay IS '0显真实号 1 不显真实号 2强制不显真实号';

COMMENT ON COLUMN t_hkp_ronglian_little_phone_note.record_file_url IS '通话录音地址';

ALTER TABLE t_hkp_ronglian_phone_note
    OWNER TO hkp;



