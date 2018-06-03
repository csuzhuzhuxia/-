/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : PostgreSQL
 Source Server Version : 100002
 Source Host           : localhost:5432
 Source Catalog        : webgis
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100002
 File Encoding         : 65001

 Date: 03/06/2018 14:55:08
*/


-- ----------------------------
-- Table structure for match_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."match_info";
CREATE TABLE "public"."match_info" (
  "miid" int4 NOT NULL DEFAULT nextval('match_info_miid_seq'::regclass),
  "matchname" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "sponser" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "undertaker" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "matchsite" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "matchstate" varchar(32) COLLATE "pg_catalog"."default" DEFAULT '未进行'::character varying,
  "matcharea" "public"."geometry",
  "beginpoint" "public"."geometry",
  "endpoint" "public"."geometry",
  "matchtime" timestamp(0) DEFAULT now()
)
;
ALTER TABLE "public"."match_info" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Records of match_info
-- ----------------------------
BEGIN;
INSERT INTO "public"."match_info" VALUES (2, '湖大友谊赛', '湖南大学', '湖南越野协会', '湘江', '0', '0103000020E61000000100000004000000E4814E4E73FA67412461016EFAEF4841A07E8A1DBDFA6741618EAD9CCBEF48412EA3B7E88BFA6741894548AB49EF4841E4814E4E73FA67412461016EFAEF4841', '0101000020E6100000D9DE748B73FA67414195FBC208F04841', '0101000020E6100000D9DE748B73FA67414195FBC208F04841', '2018-05-22 15:00:43');
INSERT INTO "public"."match_info" VALUES (1, '中南友谊赛', '中南大学', '湖南越野协会', '中南大学新校区', '1', '0103000020E61000000100000004000000E4814E4E73FA67412461016EFAEF4841A07E8A1DBDFA6741618EAD9CCBEF48412EA3B7E88BFA6741894548AB49EF4841E4814E4E73FA67412461016EFAEF4841', '0101000020E6100000D9DE748B73FA67414195FBC208F04841', '0101000020E6100000D9DE748B73FA67414195FBC208F04841', '2018-05-22 13:51:03');
COMMIT;

-- ----------------------------
-- Table structure for matchloc_1111_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_1111_1";
CREATE TABLE "public"."matchloc_1111_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_1111_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_1111_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Table structure for matchloc_zrs120_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zrs120_1";
CREATE TABLE "public"."matchloc_zrs120_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zrs120_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_zrs120_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Records of matchloc_zrs120_1
-- ----------------------------
BEGIN;
INSERT INTO "public"."matchloc_zrs120_1" VALUES (1, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs120_1" VALUES (2, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs120_1" VALUES (3, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs120_1" VALUES (4, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs120_1" VALUES (5, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs120_1" VALUES (6, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs120_1" VALUES (7, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs120_1" VALUES (8, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
COMMIT;

-- ----------------------------
-- Table structure for matchloc_zrs1234566_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zrs1234566_1";
CREATE TABLE "public"."matchloc_zrs1234566_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zrs1234566_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_zrs1234566_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Table structure for matchloc_zrs1234567_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zrs1234567_1";
CREATE TABLE "public"."matchloc_zrs1234567_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zrs1234567_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_zrs1234567_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Records of matchloc_zrs1234567_1
-- ----------------------------
BEGIN;
INSERT INTO "public"."matchloc_zrs1234567_1" VALUES (1, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs1234567_1" VALUES (2, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
COMMIT;

-- ----------------------------
-- Table structure for matchloc_zrs234_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zrs234_1";
CREATE TABLE "public"."matchloc_zrs234_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zrs234_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_zrs234_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Table structure for matchloc_zrs456_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zrs456_1";
CREATE TABLE "public"."matchloc_zrs456_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zrs456_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_zrs456_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Records of matchloc_zrs456_1
-- ----------------------------
BEGIN;
INSERT INTO "public"."matchloc_zrs456_1" VALUES (1, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
COMMIT;

-- ----------------------------
-- Table structure for matchloc_zrs675_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zrs675_1";
CREATE TABLE "public"."matchloc_zrs675_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zrs675_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_zrs675_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Table structure for matchloc_zrs987_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zrs987_1";
CREATE TABLE "public"."matchloc_zrs987_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zrs987_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_zrs987_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Table structure for matchloc_zrs_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zrs_1";
CREATE TABLE "public"."matchloc_zrs_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zrs_1_umid_seq'::regclass),
  "time" timestamp(6),
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."matchloc_zrs_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Records of matchloc_zrs_1
-- ----------------------------
BEGIN;
INSERT INTO "public"."matchloc_zrs_1" VALUES (1, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (2, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (3, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (4, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (5, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (6, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (7, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (8, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (9, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (10, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (11, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."matchloc_zrs_1" VALUES (12, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
COMMIT;

-- ----------------------------
-- Table structure for matchloc_zzx_1
-- ----------------------------
DROP TABLE IF EXISTS "public"."matchloc_zzx_1";
CREATE TABLE "public"."matchloc_zzx_1" (
  "umid" int4 NOT NULL DEFAULT nextval('matchloc_zzx_1_umid_seq'::regclass),
  "longitude" float8,
  "latitude" float8,
  "time" timestamp(0) DEFAULT now()
)
;
ALTER TABLE "public"."matchloc_zzx_1" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Records of matchloc_zzx_1
-- ----------------------------
BEGIN;
INSERT INTO "public"."matchloc_zzx_1" VALUES (52, 112.9479, 28.156861, '2018-05-24 11:22:03');
INSERT INTO "public"."matchloc_zzx_1" VALUES (53, 112.9506, 28.155652, '2018-05-24 11:22:06');
INSERT INTO "public"."matchloc_zzx_1" VALUES (54, 112.9506, 28.155652, '2018-05-24 11:22:07');
INSERT INTO "public"."matchloc_zzx_1" VALUES (55, 112.9506, 28.155652, '2018-05-24 11:22:09');
INSERT INTO "public"."matchloc_zzx_1" VALUES (56, 112.9506, 28.155652, '2018-05-24 11:22:11');
INSERT INTO "public"."matchloc_zzx_1" VALUES (57, 112.9506, 28.155652, '2018-05-24 11:22:13');
INSERT INTO "public"."matchloc_zzx_1" VALUES (58, 112.9506, 28.155652, '2018-05-24 11:22:16');
INSERT INTO "public"."matchloc_zzx_1" VALUES (59, 112.9506, 28.155652, '2018-05-24 11:22:16');
INSERT INTO "public"."matchloc_zzx_1" VALUES (60, 112.9506, 28.155652, '2018-05-24 11:22:17');
INSERT INTO "public"."matchloc_zzx_1" VALUES (61, 112.9506, 28.155652, '2018-05-24 11:22:19');
INSERT INTO "public"."matchloc_zzx_1" VALUES (62, 112.9506, 28.155652, '2018-05-24 11:22:19');
INSERT INTO "public"."matchloc_zzx_1" VALUES (63, 112.9479, 28.156861, '2018-05-24 12:15:14');
INSERT INTO "public"."matchloc_zzx_1" VALUES (64, 112.9506, 28.155652, '2018-05-24 12:15:16');
INSERT INTO "public"."matchloc_zzx_1" VALUES (65, 112.9506, 28.155652, '2018-05-24 12:15:17');
INSERT INTO "public"."matchloc_zzx_1" VALUES (66, 112.9506, 28.155652, '2018-05-24 12:15:19');
INSERT INTO "public"."matchloc_zzx_1" VALUES (67, 112.9506, 28.155652, '2018-05-24 12:15:19');
INSERT INTO "public"."matchloc_zzx_1" VALUES (68, 112.9506, 28.155652, '2018-05-24 12:15:20');
INSERT INTO "public"."matchloc_zzx_1" VALUES (69, 112.9506, 28.155652, '2018-05-24 12:15:21');
INSERT INTO "public"."matchloc_zzx_1" VALUES (70, 112.9506, 28.155652, '2018-05-24 12:15:22');
INSERT INTO "public"."matchloc_zzx_1" VALUES (71, 112.9506, 28.155652, '2018-05-24 12:15:23');
INSERT INTO "public"."matchloc_zzx_1" VALUES (72, 112.9506, 28.155652, '2018-05-24 12:15:24');
INSERT INTO "public"."matchloc_zzx_1" VALUES (73, 112.9506, 28.155652, '2018-05-24 12:15:25');
INSERT INTO "public"."matchloc_zzx_1" VALUES (74, 112.9506, 28.155652, '2018-05-24 12:15:27');
INSERT INTO "public"."matchloc_zzx_1" VALUES (75, 112.9506, 28.155652, '2018-05-24 12:15:27');
INSERT INTO "public"."matchloc_zzx_1" VALUES (76, 112.9506, 28.155652, '2018-05-24 12:15:27');
INSERT INTO "public"."matchloc_zzx_1" VALUES (77, 112.9506, 28.155652, '2018-05-24 12:15:28');
INSERT INTO "public"."matchloc_zzx_1" VALUES (78, 112.9506, 28.155652, '2018-05-24 12:15:29');
INSERT INTO "public"."matchloc_zzx_1" VALUES (79, 112.9506, 28.155652, '2018-05-24 12:15:30');
INSERT INTO "public"."matchloc_zzx_1" VALUES (80, 112.9506, 28.155652, '2018-05-24 12:15:31');
INSERT INTO "public"."matchloc_zzx_1" VALUES (81, 112.9506, 28.155652, '2018-05-24 12:15:32');
INSERT INTO "public"."matchloc_zzx_1" VALUES (82, 112.9506, 28.155652, '2018-05-24 12:15:33');
INSERT INTO "public"."matchloc_zzx_1" VALUES (83, 112.9506, 28.155652, '2018-05-24 12:15:34');
INSERT INTO "public"."matchloc_zzx_1" VALUES (84, 112.9506, 28.155652, '2018-05-24 12:15:35');
INSERT INTO "public"."matchloc_zzx_1" VALUES (85, 112.9506, 28.155652, '2018-05-24 12:15:36');
INSERT INTO "public"."matchloc_zzx_1" VALUES (86, 112.9506, 28.155652, '2018-05-24 12:15:37');
INSERT INTO "public"."matchloc_zzx_1" VALUES (87, 112.9506, 28.155652, '2018-05-24 12:15:37');
INSERT INTO "public"."matchloc_zzx_1" VALUES (88, 112.9506, 28.155652, '2018-05-24 12:15:38');
INSERT INTO "public"."matchloc_zzx_1" VALUES (89, 112.9506, 28.155652, '2018-05-24 12:15:39');
INSERT INTO "public"."matchloc_zzx_1" VALUES (90, 112.9506, 28.155652, '2018-05-24 12:15:40');
INSERT INTO "public"."matchloc_zzx_1" VALUES (91, 112.9506, 28.155652, '2018-05-24 12:15:41');
INSERT INTO "public"."matchloc_zzx_1" VALUES (92, 112.9506, 28.155652, '2018-05-24 12:15:42');
INSERT INTO "public"."matchloc_zzx_1" VALUES (93, 112.9506, 28.155652, '2018-05-24 12:15:43');
INSERT INTO "public"."matchloc_zzx_1" VALUES (94, 112.9506, 28.155652, '2018-05-24 12:15:44');
INSERT INTO "public"."matchloc_zzx_1" VALUES (95, 112.9506, 28.155652, '2018-05-24 12:15:45');
INSERT INTO "public"."matchloc_zzx_1" VALUES (96, 112.9506, 28.155652, '2018-05-24 12:15:47');
INSERT INTO "public"."matchloc_zzx_1" VALUES (97, 112.9506, 28.155652, '2018-05-24 12:15:47');
INSERT INTO "public"."matchloc_zzx_1" VALUES (98, 112.9506, 28.155652, '2018-05-24 12:15:47');
INSERT INTO "public"."matchloc_zzx_1" VALUES (99, 112.9506, 28.155652, '2018-05-24 12:15:48');
COMMIT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS "public"."message";
CREATE TABLE "public"."message" (
  "id" int4 NOT NULL DEFAULT nextval('message_id_seq'::regclass),
  "title" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "match_name" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "organizer_type" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "organizer" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "location" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "message" varchar(1024) COLLATE "pg_catalog"."default" NOT NULL,
  "datetime" varchar(50) COLLATE "pg_catalog"."default" DEFAULT now()
)
;
ALTER TABLE "public"."message" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Table structure for task_table
-- ----------------------------
DROP TABLE IF EXISTS "public"."task_table";
CREATE TABLE "public"."task_table" (
  "matchname" varchar(32) COLLATE "pg_catalog"."default",
  "taskid" int4,
  "taskpoint" "public"."geometry",
  "question" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "answera" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "answerb" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "answerc" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "rightanswer" varchar(32) COLLATE "pg_catalog"."default" NOT NULL
)
;
ALTER TABLE "public"."task_table" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info";
CREATE TABLE "public"."user_info" (
  "userid" int4 NOT NULL DEFAULT nextval('user_info_userid_seq'::regclass),
  "username" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "telphone" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "sex" varchar(3) COLLATE "pg_catalog"."default",
  "url" varchar(30) COLLATE "pg_catalog"."default",
  "sign" varchar(100) COLLATE "pg_catalog"."default",
  "match_times" int4 DEFAULT 0,
  "nickname" varchar(20) COLLATE "pg_catalog"."default",
  "is_first_login" varchar(1) COLLATE "pg_catalog"."default" DEFAULT '0'::character varying
)
;
ALTER TABLE "public"."user_info" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO "public"."user_info" VALUES (1, '朱润松', 'zrs123', '2956054174@qq.com', '18373150759', NULL, NULL, NULL, 0, NULL, '0');
INSERT INTO "public"."user_info" VALUES (3, '12346', '111', '2956054143@qq.com', '', NULL, NULL, NULL, 0, NULL, '0');
INSERT INTO "public"."user_info" VALUES (4, '123123', '12', 'hshsh', 'nshjsj', '男', NULL, 'bdhje', 0, 'zghjjs', '1');
INSERT INTO "public"."user_info" VALUES (6, 'zrs1', 'zrs1', 'bbnm', 'bbnm', '男', NULL, 'bbnm', 0, 'ggbb', '1');
INSERT INTO "public"."user_info" VALUES (8, '12345', '123', '', '', NULL, NULL, NULL, 0, NULL, '0');
INSERT INTO "public"."user_info" VALUES (9, '1235', '1234', '', 'hh', NULL, NULL, NULL, 0, NULL, '1');
INSERT INTO "public"."user_info" VALUES (20, 'zrs675', 'zrs19980711', '135666@qq.cpom', 'fdajlk;a', '男', NULL, NULL, 1, NULL, '1');
INSERT INTO "public"."user_info" VALUES (21, 'zrs1234567', 'zrs12', 'jfadlj', '', '男', NULL, 'hello', 1, NULL, '1');
INSERT INTO "public"."user_info" VALUES (2, 'zrs', 'zrs123', '2956054174@163.cofhfm', '18373150759989', '女', NULL, 'I woshwo1jlkyih', 1, 'zhzuhu1Lk', '1');
INSERT INTO "public"."user_info" VALUES (5, '1111', '1', '', '', NULL, NULL, NULL, 1, 'lj;al', '1');
INSERT INTO "public"."user_info" VALUES (10, 'zhuzhuxia', 'zrs123123', '', '', NULL, NULL, NULL, 0, NULL, '0');
INSERT INTO "public"."user_info" VALUES (11, 'zrs183', 'zrs19980711', '', '', NULL, NULL, NULL, 0, NULL, '0');
INSERT INTO "public"."user_info" VALUES (7, 'zzx', '1', 'vnmm', '1345677', '女', NULL, 'vnn', 1, 'gjk', '1');
INSERT INTO "public"."user_info" VALUES (12, 'zzx123', 'zrs19980711', '', '', NULL, NULL, NULL, 0, NULL, '0');
INSERT INTO "public"."user_info" VALUES (13, 'zrs1208', 'zrs19980711', '5766889789@45', 'yuio1', '男', NULL, 'woodre', 0, 'wodr1', '1');
INSERT INTO "public"."user_info" VALUES (14, 'zrs120', 'zrs19980711', '', '', NULL, NULL, NULL, 1, NULL, '1');
INSERT INTO "public"."user_info" VALUES (15, 'zrs12345', 'zrs19980711', '67788@qqcom', '1111', '男', NULL, 'wijiushiwo1', 0, 'zhuzhuxia', '1');
INSERT INTO "public"."user_info" VALUES (16, 'zrs1234566', 'zrs19980711', '', '', NULL, NULL, 'uytu1', 1, NULL, '1');
INSERT INTO "public"."user_info" VALUES (17, 'zrs987', 'zrs19980711', '', '', NULL, NULL, NULL, 1, NULL, '1');
INSERT INTO "public"."user_info" VALUES (18, 'zrs456', 'zrs19980711', '', '', NULL, NULL, NULL, 1, NULL, '1');
INSERT INTO "public"."user_info" VALUES (19, 'zrs234', 'zrs19980711', '', '12345678', '男', NULL, 'gjhjh', 1, 'hzuzhuxia', '1');
COMMIT;

-- ----------------------------
-- Table structure for user_match
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_match";
CREATE TABLE "public"."user_match" (
  "umid" int4 NOT NULL DEFAULT nextval('user_match_umid_seq'::regclass),
  "username" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "matchname" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "begintime" timestamp(6),
  "endtime" timestamp(6),
  "totaltime" float8,
  "matchrank" int4,
  "longitude" float8,
  "latitude" float8
)
;
ALTER TABLE "public"."user_match" OWNER TO "zhuzhuxia";

-- ----------------------------
-- Records of user_match
-- ----------------------------
BEGIN;
INSERT INTO "public"."user_match" VALUES (15, 'zzx', '中南友谊赛', NULL, NULL, NULL, NULL, 112.943183, 28.168129);
INSERT INTO "public"."user_match" VALUES (24, '1111', '中南友谊赛', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."user_match" VALUES (16, 'zrs', '中南友谊赛', NULL, NULL, NULL, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."user_match" VALUES (17, 'zrs120', '中南友谊赛', NULL, NULL, NULL, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."user_match" VALUES (18, 'zrs1234566', '中南友谊赛', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."user_match" VALUES (19, 'zrs987', '中南友谊赛', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."user_match" VALUES (20, 'zrs456', '中南友谊赛', NULL, NULL, NULL, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
INSERT INTO "public"."user_match" VALUES (21, 'zrs234', '中南友谊赛', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."user_match" VALUES (22, 'zrs675', '中南友谊赛', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."user_match" VALUES (23, 'zrs1234567', '中南友谊赛', NULL, NULL, NULL, NULL, 4.94065645841247e-324, 4.94065645841247e-324);
COMMIT;

-- ----------------------------
-- Primary Key structure for table match_info
-- ----------------------------
ALTER TABLE "public"."match_info" ADD CONSTRAINT "match_info_pkey" PRIMARY KEY ("matchname");

-- ----------------------------
-- Primary Key structure for table message
-- ----------------------------
ALTER TABLE "public"."message" ADD CONSTRAINT "message_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_info
-- ----------------------------
ALTER TABLE "public"."user_info" ADD CONSTRAINT "user_info_pkey" PRIMARY KEY ("username");
