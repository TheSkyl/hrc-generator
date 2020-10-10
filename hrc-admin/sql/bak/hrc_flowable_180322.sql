-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: mysql_serv    Database: hrc_flowable
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ACT_EVT_LOG`
--

DROP TABLE IF EXISTS `ACT_EVT_LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_EVT_LOG` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_EVT_LOG`
--

LOCK TABLES `ACT_EVT_LOG` WRITE;
/*!40000 ALTER TABLE `ACT_EVT_LOG` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_EVT_LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_GE_BYTEARRAY`
--

DROP TABLE IF EXISTS `ACT_GE_BYTEARRAY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_GE_BYTEARRAY` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_GE_BYTEARRAY`
--

LOCK TABLES `ACT_GE_BYTEARRAY` WRITE;
/*!40000 ALTER TABLE `ACT_GE_BYTEARRAY` DISABLE KEYS */;
INSERT INTO `ACT_GE_BYTEARRAY` VALUES ('2',1,'/home/frank/Workspace/cebrains/ceb-hrc/hrc-admin/target/classes/processes/ExpenseProcess.bpmn20.xml','1','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n             xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\"\n             xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\"\n             typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\"\n             targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"Expense\" name=\"ExpenseProcess\" isExecutable=\"true\">\n    <documentation>报销流程</documentation>\n    <startEvent id=\"start\" name=\"开始\n\"></startEvent>\n    <userTask id=\"fillTask\" name=\"出差报销\" flowable:assignee=\"${taskUser}\">\n      <extensionElements>\n        <modeler:initiator-can-complete xmlns:modeler=\"http://flowable.org/modeler\"><![CDATA[false]]></modeler:initiator-can-complete>\n      </extensionElements>\n    </userTask>\n    <exclusiveGateway id=\"judgeTask\"></exclusiveGateway>\n    <userTask id=\"directorTak\" name=\"经理审批\">\n      <extensionElements>\n        <flowable:taskListener event=\"create\" class=\"com.cebrains.hrc.modular.flowable.handler.ManagerTaskHandler\"></flowable:taskListener>\n      </extensionElements>\n    </userTask>\n    <userTask id=\"bossTask\" name=\"老板审批\">\n      <extensionElements>\n        <flowable:taskListener event=\"create\" class=\"com.cebrains.hrc.modular.flowable.handler.BossTaskHandler\"></flowable:taskListener>\n      </extensionElements>\n    </userTask>\n    <endEvent id=\"end\" name=\"结束\"></endEvent>\n    <sequenceFlow id=\"directorNotPassFlow\" name=\"驳回\" sourceRef=\"directorTak\" targetRef=\"fillTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'驳回\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"bossNotPassFlow\" name=\"驳回\" sourceRef=\"bossTask\" targetRef=\"fillTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'驳回\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow1\" sourceRef=\"start\" targetRef=\"fillTask\"></sequenceFlow>\n    <sequenceFlow id=\"flow2\" sourceRef=\"fillTask\" targetRef=\"judgeTask\"></sequenceFlow>\n    <sequenceFlow id=\"judgeMore\" name=\"大于500元\" sourceRef=\"judgeTask\" targetRef=\"bossTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money > 500}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"bossPassFlow\" name=\"通过\" sourceRef=\"bossTask\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'通过\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"directorPassFlow\" name=\"通过\" sourceRef=\"directorTak\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'通过\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"judgeLess\" name=\"小于500元\" sourceRef=\"judgeTask\" targetRef=\"directorTak\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money <= 500}]]></conditionExpression>\n    </sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_Expense\">\n    <bpmndi:BPMNPlane bpmnElement=\"Expense\" id=\"BPMNPlane_Expense\">\n      <bpmndi:BPMNShape bpmnElement=\"start\" id=\"BPMNShape_start\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"285.0\" y=\"135.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"fillTask\" id=\"BPMNShape_fillTask\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"405.0\" y=\"110.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"judgeTask\" id=\"BPMNShape_judgeTask\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"585.0\" y=\"130.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"directorTak\" id=\"BPMNShape_directorTak\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"735.0\" y=\"110.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"bossTask\" id=\"BPMNShape_bossTask\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"555.0\" y=\"255.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"end\" id=\"BPMNShape_end\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"771.0\" y=\"281.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">\n        <omgdi:waypoint x=\"315.0\" y=\"150.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"405.0\" y=\"150.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">\n        <omgdi:waypoint x=\"505.0\" y=\"150.16611295681062\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"585.4333333333333\" y=\"150.43333333333334\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"judgeLess\" id=\"BPMNEdge_judgeLess\">\n        <omgdi:waypoint x=\"624.5530726256983\" y=\"150.44692737430168\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"735.0\" y=\"150.1392757660167\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"directorNotPassFlow\" id=\"BPMNEdge_directorNotPassFlow\">\n        <omgdi:waypoint x=\"785.0\" y=\"110.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"37.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"37.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"110.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"bossPassFlow\" id=\"BPMNEdge_bossPassFlow\">\n        <omgdi:waypoint x=\"655.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"771.0\" y=\"295.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"judgeMore\" id=\"BPMNEdge_judgeMore\">\n        <omgdi:waypoint x=\"605.4340277777778\" y=\"169.56597222222223\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"605.1384083044983\" y=\"255.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"directorPassFlow\" id=\"BPMNEdge_directorPassFlow\">\n        <omgdi:waypoint x=\"785.0\" y=\"190.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"281.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"bossNotPassFlow\" id=\"BPMNEdge_bossNotPassFlow\">\n        <omgdi:waypoint x=\"555.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"190.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('2502',1,'/home/frank/Workspace/cebrains/Server/hrc/hrc-admin/target/classes/processes/ExpenseProcess.bpmn20.xml','2501','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n             xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\"\n             xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\"\n             typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\"\n             targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"Expense\" name=\"ExpenseProcess\" isExecutable=\"true\">\n    <documentation>报销流程</documentation>\n    <startEvent id=\"start\" name=\"开始\n\"></startEvent>\n    <userTask id=\"fillTask\" name=\"出差报销\" flowable:assignee=\"${taskUser}\">\n      <extensionElements>\n        <modeler:initiator-can-complete xmlns:modeler=\"http://flowable.org/modeler\"><![CDATA[false]]></modeler:initiator-can-complete>\n      </extensionElements>\n    </userTask>\n    <exclusiveGateway id=\"judgeTask\"></exclusiveGateway>\n    <userTask id=\"directorTak\" name=\"经理审批\">\n      <extensionElements>\n        <flowable:taskListener event=\"create\" class=\"com.cebrains.hrc.modular.flowable.handler.ManagerTaskHandler\"></flowable:taskListener>\n      </extensionElements>\n    </userTask>\n    <userTask id=\"bossTask\" name=\"老板审批\">\n      <extensionElements>\n        <flowable:taskListener event=\"create\" class=\"com.cebrains.hrc.modular.flowable.handler.BossTaskHandler\"></flowable:taskListener>\n      </extensionElements>\n    </userTask>\n    <endEvent id=\"end\" name=\"结束\"></endEvent>\n    <sequenceFlow id=\"directorNotPassFlow\" name=\"驳回\" sourceRef=\"directorTak\" targetRef=\"fillTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'驳回\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"bossNotPassFlow\" name=\"驳回\" sourceRef=\"bossTask\" targetRef=\"fillTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'驳回\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow1\" sourceRef=\"start\" targetRef=\"fillTask\"></sequenceFlow>\n    <sequenceFlow id=\"flow2\" sourceRef=\"fillTask\" targetRef=\"judgeTask\"></sequenceFlow>\n    <sequenceFlow id=\"judgeMore\" name=\"大于500元\" sourceRef=\"judgeTask\" targetRef=\"bossTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money > 500}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"bossPassFlow\" name=\"通过\" sourceRef=\"bossTask\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'通过\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"directorPassFlow\" name=\"通过\" sourceRef=\"directorTak\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'通过\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"judgeLess\" name=\"小于500元\" sourceRef=\"judgeTask\" targetRef=\"directorTak\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money <= 500}]]></conditionExpression>\n    </sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_Expense\">\n    <bpmndi:BPMNPlane bpmnElement=\"Expense\" id=\"BPMNPlane_Expense\">\n      <bpmndi:BPMNShape bpmnElement=\"start\" id=\"BPMNShape_start\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"285.0\" y=\"135.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"fillTask\" id=\"BPMNShape_fillTask\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"405.0\" y=\"110.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"judgeTask\" id=\"BPMNShape_judgeTask\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"585.0\" y=\"130.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"directorTak\" id=\"BPMNShape_directorTak\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"735.0\" y=\"110.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"bossTask\" id=\"BPMNShape_bossTask\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"555.0\" y=\"255.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"end\" id=\"BPMNShape_end\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"771.0\" y=\"281.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">\n        <omgdi:waypoint x=\"315.0\" y=\"150.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"405.0\" y=\"150.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">\n        <omgdi:waypoint x=\"505.0\" y=\"150.16611295681062\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"585.4333333333333\" y=\"150.43333333333334\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"judgeLess\" id=\"BPMNEdge_judgeLess\">\n        <omgdi:waypoint x=\"624.5530726256983\" y=\"150.44692737430168\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"735.0\" y=\"150.1392757660167\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"directorNotPassFlow\" id=\"BPMNEdge_directorNotPassFlow\">\n        <omgdi:waypoint x=\"785.0\" y=\"110.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"37.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"37.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"110.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"bossPassFlow\" id=\"BPMNEdge_bossPassFlow\">\n        <omgdi:waypoint x=\"655.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"771.0\" y=\"295.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"judgeMore\" id=\"BPMNEdge_judgeMore\">\n        <omgdi:waypoint x=\"605.4340277777778\" y=\"169.56597222222223\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"605.1384083044983\" y=\"255.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"directorPassFlow\" id=\"BPMNEdge_directorPassFlow\">\n        <omgdi:waypoint x=\"785.0\" y=\"190.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"281.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"bossNotPassFlow\" id=\"BPMNEdge_bossNotPassFlow\">\n        <omgdi:waypoint x=\"555.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"190.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('2503',1,'/home/frank/Workspace/cebrains/Server/hrc/hrc-admin/target/classes/processes/ExpenseProcess.Expense.png','2501','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0M\0\0Y\0\0\0D\�Ĉ\0\0.�IDATx\�\�\���u}?��!*�Zu��e\��~�?�:G[�:آ�R4s\\.\��]0�\�n�AA\�!\�4\n%�ݤQ��P\ZhJ�\�v�$\�OL��� $��	\r&AH�\��yrO\�\\�\�r�w����\�\�\�>{�{�\�\�\�\��lK\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\\N?��\�0\�|x�\0�&M��Ռ�K�\�*\�Z�J������\�\�]s{\�1\�s���\�\�n/�N\0�ƥ\�\�[_W쩝��P�^7��\�P,W�ʵ����޿̂U�y�S\�\�O-�+\�{*\�\�*U��P\'\0\�\0\�V\�0��\�\�i�\����U\�v�M]\�\�\�L���mv�\�\�\�\�j\�^D�\04�K1�T(UO+�\�.=o^\�\�.(׎�Y�¼\�\�\�f�J��\�\�^�=^�\�\nsjU,\�N�\'Q\'\0\�\0\�\���\�\n=�/\�\�˲ey=\�/\�达v\�n��Ti+ί\�*�:\�6\�\�*\��%\�k�\'Q\'\0\�\0\�N\�\�\�\�LQW�����C��4��ڭ��PW�:+R\�yP�\�I�4/B�=�:	\0h���\\�\��\�\"i�\"BԴҲ#c&�P�>3P��\�\�F���byތ�\�\'\�\�Ү�\�ٓ��\0�f���J���\��\��ť\�\�=\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0@�\0̀f\0@�\04\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�T\'@3\0�:	\0h\0\�I\0@3\0�N\0��C\�\��\\�TJ\�Z�n{\0�&\0\�$Ir\�o~󛍩����\�\�\0�\0��f͚\�\'}\�\�ummm�\�+\0 4�z�\��d��\�;�:�f\�Z\�\�\��{\0�&���~��\�I?�6mZ\�\�\�\�c\�\0�\�\�Ԟ~�\�˒,\\�p}Z7O��\0`��E��\�S@�Js\�n۶\�w��;v�T,WL�0\�p{\0\�gh:!/�Z[[O���f�v\�\���!�^�zM[[[�\����\�$4\�\�\�S��\�{\�Ce�d\�֭�D\�\�\�\�8\����N(4��.\�\��կ�7\��\�L�\\r\�\�4��N�\�mz\�,@K\�-�\��\�\�?~�Yy\0h�дǹM\�e\�i\�ĉǤ\�hk��@�loo_\�\�y\0\�$Ґ�\�,@cW^ye9\rI\�\�Cӵ\�^{]Z;\�\�;\0\�<�\�\�u\�2�l�\0�*\�HZ�x���)���:::\�b\�\0@\�\�m2\����\�?�c7nܖ�����\�\�r\0�9CSv%={��\�]*�\�υ�\0S�$��^���x`{�RI�,Y2&G����o\�\�\�M�-[�\�\�\�u�`��\�+���\�~�:	\0\����\�˓\r6$/���1B\��\�O�.]�B\�|\�Q	\�\��\�֭S\'`_\�;�Өi^Z�dɣ�JP\'\ru�Q$��x!=#m^vT�:i��\0�\"�V܋�j\\\�\�IC�`,6/nz6Y�\��\�\�z����:\���\0uR�T\'h�f`�\�u\�\�^�����vq_<\�E\\3\0\�:�N\�\�\�����\��@>\��l�q\�\0��\�:	@s7�־>`3�y\��:�N��\04u3�rI��\�@<\�E\\3\0\�:�N�\�h@�T\'\�I\04�G\\j�f �\"�\0uR�T\'h\�f`ս�l\�1/\�P\'\�Iu��n~�nE��G3�\\r�\��y\��:�N��\04u3\�ɇoأ����k@�T\'\�I\04۶%�\��\�KN\��\�1/\�P\'\�Iu��m\�\�yoy����O�\��:�N��\04_3�m[�\�j�_�/�\�Gl\�z7U3\0\�:�N\�\��P\�z7U3\0\�:�N\�\�\��޼k:ػ�^\�5\�\�[\�\�Iu\0\��/H�\�\�Ë�f`�89I\�-\�:�N�04\�9)\�1�\���D�4\�I\04�f�\�S�b�\�y�I�4\�I\04�f�=\�P��N\Z\�$\0�C3\�4N\"\�;\�I�4\�I�\�K�\�+VL�\����t�\�\�/L�>�厎�\��\�OO&O��\�_�\�K�^z\�\��\�7�\��\�c�C30J\�P�Ȍ�:i��\0\�K�\�~�\rW\\q\�˅B!Y�`A��$\�֭K�nݚ��ݰaC�\��8ICU\�\�յ�X,�����{P3`hF��Z�7�4\�\�Q\'\ruhVi8:\�\��\�\�]t\�EY z\�W���\�#�$_�җ^�2eʣ\'N<\�\�\���L�\�\��6NꤡN4�f�\�\��\�\�瞛,Z�hXa��ŋ\'g�u\��N�C30�Ӂz>ꤡN\�50\�s\�=N�6-�\�/���կ�4�m9\�sγ�5�\��!2\�E�\�A�4\�I`���Lq�ҁ?���ss[[\�d{Y3�\����Ό�:i��\0;\�9L�$\�@\�05�q:\�36��\�8{[3�\� \�����3�n����\\B�4\�I�\�\�U�����\�0Lw\�uז����\�q̀f���N�>\�E�\�\�\�\�4\��\'u\�P\'�f�p\�\���\��\�w�K��S���LO3�\� �pf_�I��\�S>Ψ{\�9NꤡN\�(f�\�s�\�\�\�O�ӗ\�6i4@�Fh�\�\�h�kl\�\'u\�P\'�f�bŊ)q�����p�u\�Y\�O?��w\������mI:�L3�h�\�y��t�0�P�ഷ��p�TO�4\�I�|\�{\�[z\�\�&�Ҝ9sICӅ���K�_#��\�\�\�k4\�p<\�cp*8\r70\���\�[\�8���:	��g\�~\�8��\��\��U�B�\�\�\��f�n\�\�\�Ùf`<\�\r\�\����\�a��r\�qR\'\ruhӧO�\�g�=��i���\��5��mv��Z\�h4\�\�x\�\��4Xp�\�~�����N\Z\�$0Vttt$[�n=��)~_�µ\�\�?�\�@�;��E3�O\�s\��\�\�4\nN���\'\�8���:	�\����/f\�c��\�O~�<���\�pL\��8\�\��p3g?\�\�a\rf�~\�w���\�\�N\ZB0N�y\�;�LӖ-[֛i:h\�>\�\�\�zʄ	�\�FC3p\�e�e�\�ʕ+�\��裏N&M��\�F\�s\�i�m_��\�\'\�w\�^�;��\�$\�|\�;w}?��3\�HV�X��>\��\�?�[��Z\��\�4\r\�p\�4���k\�ʵ��mS�\�{b�\\���T�*n��\�??\������\�(�*m\�}yT\�\�o�1����\�\�߼ys�t\�\�AC_^K_�\����y\�l��\�\�}O<�\���6m\�K�����8�\�9M�\�\�\�\\���\��\�Of/\�?��ϓ�7f/�m�\�\��6��\�{��&\�|\\�~�m\��\�\�c�X�\"�\�\�~7��@\�\�={v\�\�\�p/��®\�\�q\�T5��x�(�[\�9M������\�\�\�\�]\�ڿ�\���#�=�\�7U�\�c\�H�Q��rT\��\�\\�\���;ұ%��1��\��\�iŞڙip:~�\�\�8\��\�/g�妛n\�U\����~��\�\�n\��\�\�v֬Y\�\�~��\��s��\�n\�y�ᇓ�\�;���\�oϞ\�}ӛޔ���o\�jm�Ϸ���ݾ\�{\�#4���\\u����\���\�]=\�5������:@s9b\�@�E�e_\�;�yh\�\�\�l��X��\���������\�SO=5\�\�Q�s\�6�ϛ��\�\�}�}\�k�p3^�݉\'����hH4c\�xnT�vfg�ˊ7�z\�p�SS_=\�r\�44�=p�\�>,�)�*���\�?�!\�#d��\�#�\�\�\�\�/6zn�]O%�h�l��T�n��w�wͫ�+f��\�\�s��3\��u2\��Po�\�Eq߃>������E�]<���\r�<4U*�=PԸ��\�ah�7�\�Gy$�]�vm�>�\�Og_M@ӻ�\�+�=ԟ\�4s\�̻|N\�~7���?⡩�8f����\�с�3FӧO϶�\�?�ӆ\�\�x�PH\���7$�^ziv\�7��8�v5�ۯZ�*����\'y�ߜ\�t\�s���\�䪫�\��4��x 8\rX�����\0\��Nhi\�\�i\�*W?��r\�\�bO\�\�\�)�����ko��ϛ\���Hi��B,\�\�o��9s�J��]:�ExJƂ��*\�\��\��\�\'\�_���\�\�_\��=\�iX�v��\�\�c��N\�5)�ֵ�-]�o\�\�\�F�\�y�7�\���\�\�ncyq\�F�����_\�\\sM����=[��M�v�V\�~g^\'\�6�+�\��f�\�I�)|\�+_�\�\���W^y\�]<oҤIO�/f\����\nMq�x�=�\�s��_\�r�Xv2\��x��\�!\�ϻ\���\�\�/Ξw\�9\�4\�>O�N?��\�p\�\r\�;��ꗵ\�\�\�o\'O���X�fͮY�h4c\�P3={���\�\rN��S�m�G�\�S��ΠR����\�\�e�T9?�\�5Y�\�|��\��>R(\�\�w�\����\���z\�@�\��\�N˖\�*WŒ�\�Z\'# \��Lq�\�yCQ�b�\\<�\��\�R\�|��U�\�z|u.~�F�\�1s\�ҷ\\9\���:u\�7�>��O\�qnT��/_�\�\�yQ�\�9�\�$0\�͘1\����Ǉjiލi`Zn��\�\�/��\�[�</\�]��\�h9\\��=�]�����\�\�\�\�b�(\�u\�Ot��.q[.�wm�;���\\�Xj��c_\�K��/m\��)\�8\�m`\Znp\�_\���\�0\�e\�R^\r0;o�:��A �.��*W\�M��W���r�u��\�\�i�eGK�+��\�o\�\�R\�{G[��\�+f�\�wG��Y�\�\��x�\Z]���\�\�5�x윣���������Y��˩�Y��\�y�\�C�s�\0��}�\�/�\����l\�\�I�&����m��>�CS��e��<�Dx\�T�\�D\�y\��޷\�\�8�)\��7���Nm,\��\�g?�-O��u�\�7\\�W\�@Ļ��L\�G?�\�]K]\Z5)��1%f��/�;�e��\�\�(8�\�\��4�\�\��H}d�\�\�l���[c݇\�\�~2�yk\�B\�R��4[�W��K�\"�\rtv���\�_\rS\�\�WӲ#c\�\�hMQ�b�^�FRK�evyX\�\�P�\�G>�\��x^,enb�<�\�\�Ҽ3fd�R�����^-u�\�\�R�)�#~Wlo,���N�ڔ)SVD1=�\����e\Z\�y��Д/\r�w)�s�\�Ν��F�)�\��w�c���Q�wS\�\�{����\'6ǒ���\�g\�\�6�w^��H��c�9f��	�\�\��5\�\"8\�_�>\0\r\�kz����]\�a�ˍ\���pC�T�\�\��4$m\�j^�\�TK\�\�\��87*��󝊥\�\�q1��\��ү�b�[�vv���J��\�X�7�\�d���\�\��Y�>�OdK�\�\�7��%\�w\�u׮�\�\�\�S,��\�\����&ұ\��\�`\rvY�|\�*�7\�m���ʧq��������@Ӌs�\�:\�-��\�/J`Z�bŝ\�\�x���\�8{{���+��b\�\��\�\��Mxb\�H��\�x\�4Ų�8*\Z�\�>.�P���$\�N�h���x�6\r\�}\�H4?�яv-\�$�\�/L�(\���\��^�3Z���\�\�\�χjZ�9\�ޙ_ B�\�9\"\�y��u~!��1�\�*ň����3ތ:ꨣ�7��\�F�Al����\�_\�C�\�y\0}&O�\�>eʔ\�nذ\���k\��ǤI���,�yB\�`\�@���\�\��wV30\�\��j��uřZ\�\�&\��<TWU\'�q�����\�\�\�\�j\�)f�\"0���~\�\�\�4�\���\�Դ\\�N\Z\�$�Ҁs�\�ɓ_�\�;^܏�Cl����\�cI^::\�Ù�8\��wI�%yꤡN.\�q�4iReʔ)\��l\�\�0�\�G?�9\r]\�E�ä04� 8\rw�\�E\�IC�Vx�|�����\r�g\��\��\�<�nݺg㼔�e˖�\�<�\�/z{{�����\����\��bv\�U�4�f`\�;K�\�IC�\�7i�����B\Z�nKǓ\�\�\Z�!\�]��E\�0f�\�-̀���\�tlni\��U\'\�\'u\0̀�\�\�@t� �?/0���:	�f�\�4��f�,\�S\'\ru\0̀�`��$0��\�:	�f���f��\��	uR�@3\�EX3@c\'\�\�lZ\\V\\�T\'\�I\04^�54\��P\'\�\'u\0̀a\�\0B\�:	\0�\�\0B\�:	\0�\�\0B\�:	\0�\�\0B\�:	\0�\�\0BꤡN�04BꤡN�04BꤡN�04BꤡN�04BꤡN�04M��\�$\0h4M��\�$\0h4M��\�$\0h4M��\�$\0h4M���:	�f@3��P\'\ru�Q�R�x=ck\��\��P\'\ru�Qdٲe\�nذ��(\�֭�%muT\nM���:	�(R�V?{\�=�lz\�\�xA�wN�\�\�\�}*�wT\nM��)p��\0��\��S�,Y�P,y��\�cm\�|�\�I4�c�\�\�7b�?��P\'\�x���R��,\\�P��&mlEs;a\�\�\r�&\�]�$G�\�7�٘J:;;�\��&\�\�\��ᴱ}9�۶���\��&\�ݚ5k����\��֥��T{\0�+4-�ƶo<d� 4��^|�\�?پ}���άY�\�ttt�\�\��\�hhO\�g�򑆨S\��&\�i�����~6mڴ����\�\��\�hh\��|�ɹMM\�\���\�O_�`\�\�\�\'{	\0\�w3�\�,S>�\�Gh�٥�\��m\����Bӎ;^*�+�\�\0㻙��Q`\��\�CM4��k\�ޟa�\�\�k\�\�\�\n�\0�\�Fv�Y&\�6!4AK˽�\�;TfJ�n\�zO\�G\�c\00�\Z\�E�&W\�Ch�\�}��_=o�\�y��.�\��i\0\�-8�hZ�\�r\�/JL�?��5��\�9M\0��\�Mk\�ĉǤ\�hk��@�loo_\�\�y\0��\�M\�\�+�,�!i{}h��\�k�kmm�c\�\0�\�W4�X~�x�\�\rul{Sz\\�\�\�\�x��\0�[p\\A\���\��\�ƍ��#\�\�\�\��Yv\0��\���`���K�R|\�B{\04�ข~b9^\�qq{\04�\�\�3\0��q�g\0@3�\�\n\�\0�f\0\�8�\0\�\0�+p<\0��\�\�\�\�>̞A�\040�qU\�]Z(\��p\�¼\�\�\�e]�\�Uq[,������v\�\�}G�Ti+����ˡN\0�F\�*���Ϙ��衞\�}}\�bO\��\rCU��X:�|������5���tl�mf\�\�=v��\�CZ��vf\Z���WC�\04�\�qU(WK��\n=\�]�\��=\�Rw�a�k\�J�BWO��Ґ�]\�ڿ��\�#�\�\�\�\�/6�}\�v=���c��T�n��\�]0��iJC\�7�\�$\0�\��W\�\�\�}[�\\�\�Ji`YR쩝<����\��~/\�6������/\�r��v�`4���t�ߥc[��a\�ݵËsz�8���WT�3Ş\�?t|���Ŀ��\���͍\��\�P\'\0\�\0#v\\u�*\�\�L\�\���7�ܮRuVw���l���C\�G\n�Z�\�Q�>l\��\�\���\�﫧eK�J��bI��\Z\�$\0�`D��i�eG\�lN��\Z\��\�\�_�bV�\\97�z^��ʵց\�\��M\�R\�,��kg\��\�i�\����N\0�\�qK\�\"�\�\��J��#@\�/\�\�#4�j���\�ܦ=\�O��hM���\� ��K��zq\�T��D\�T\rtv���\�_\rS\�\�\�^u�� \�$\0�\�Wq��+�O\��ĹD�/�K\�\�\�\r\�Rev�����M}W͋s�ji��`ܟ\�`�\�\��N\�R�Ḙ\�@�\�}\�\�Q�sc�)�\�\�W�?]zu\0\�0j��e\�ܛ�@�\048���\0hp\\�\�\0\�\�\�3\0�\0\��g\0@3\0�+\�\0�f\0W\�x\048���\0hp\\�\�\0\�\�\�3\0��q�g\0@3�\�\n\�\0�f\0\�8�\0\�\0�+\��g\0@3\0�+\�--\�ݵ\�\�%\0\�܂\�\n\�s�P��\Z�̫��X�N-�\�\�,����\�I\�;�kn\����\�R�/\n�zO�7@s�+\���\�U��\�\�ޟ�4z*_h��i�eGv�+\�7c�ң����G@s�+\����L��\�b�vR�T]\���X�׹`\�\�{��i��0��\�R\�⮹\�٣\0��\�\�\�xvD6\�T�.��\n\�\�\��\�\��H���\�g�T?��Jq~\�|V*=	\0�[p\\1��\�b��ϧ�\�\�7�\��\�b�rnWO�\�\�=\�J�o�N\�.e\�)W�v\�s+�\�I\0\�܂\�q}<\�9L\�r�rmf,�\�*\�\�.\�Y�\�4 \�\����Ruzܞ7��mq��yWؓ\0��\�\��x.�*m1�\�\�f\�\�=6\�\�K�\�_\�\nK�j�d\�;�*�U;/Q;;f�\�I\0\�܂\�q{<\�_��kn\��J\�Y\�R\�\�4]3M�dog�zu���ko��\n�\�\�tܑ�\�\�\�({\04�ย)�\�\�CW���\�\�[_7\�\�p�q\0\�\�\�\�\�Т8��\�S��\0�f�f=�NH\�˃��\�\�\�S\�)\�I\0@3@3Gw�\�0a\�\��\�$\0�����\nMmmm�\�C��\0�f\0\�R\�s�2˄:	\0h���M\�eB�\04P\'\rI\�2�N\0�84}�\�\\��\�\�I\0@3\0{SٹMf�P\'\0\�\04>�Np\\�N\0��$I_�z�m<���J��,Y�\�8ģ��7Y�l\��c�#u\0\��2��/_�lذ!y饗�\Z\�?�|�t\�\�\�\0�yG%\�$\0h4�H\�0	L�&8��dɒG���\0�\��\"�$O`=#\rM/;*Q\'@3�E\�\ZaeT�&��N�f@30CӋ��MV?t}�ޯe#����	u\0\�4}hڼq]��%?�\�\�F\��	;B\�$\0�h\�д\�g��G`\�\�ڟ-v�&\�I\0@3\�ܡ\�\�\�\�M�#4�N\0���M+�t�\�1aGhB�\04B�\�$4�N\0����\�\r�\�1aGhB�\04M�V\�;o�\��	;B\�$\0�h\�\��\�u+��?��\�Ҽ��xL\��P\'\0\�@S��O>|\��)\�t�&\�I\0@3 4mۖ��\�{.\�K\�Ǆ�	u\0\�4mhڼq]�\�{\��\��\�6�\�\�>\�\�EQ�\�S\0 41\�BӶmɳ��\�-�d����\�&�5\�$4�O5�t�<Xhjmm=Ş\0��Q���]2\�$4q�\�\�݃���\'L�p��\0B�(4\�\�\�\�`�N\�İ\�\�	�����S\�!\0�e�i_S>���}���\�mz\�,\0M�\�\�dM�Hm\�\�\�&\�2�Є\�dM\�IC\�B�L\0�\�$4B��ם\�t�=\0BB�!4�g�\�\�m2\�\0BB�!4�joo��\�\�\��\�\�x[:V�\�}�M[\��dߒ�c;{\0�&�&�Ihj\Zq)�t,I\�K�}�m�QK��l\��Є\�$41�k\�\�\�xA�\�X>q\�\�c\�M\0���&ƕ\�\�\�\�\'M����\0\�\�ޞ��\��}R�V���~:�����$����M֭[�\�{\�ɜ9s�/|\���\�f�N\0 4!4	M���tJ\Zr^�K7\�xc�aÆdolܸ1\�>�W�ҟy��\0BB�\�\�XL�\��3fd�J�\"�w\�E�Nf�\0@hBh��5\�\��K�fΜ�k	޾��{�f\�\�T\�8{\0�&�&��1\'\rL��3L��\�S��\��6\0MMBcJ\\V���}]�7\�R��s�,\�\0��}0aÅ�����{2\�BӒ<\�\�E��o�\�l\0M싴��õk\�\��+�\�W�r�@Ͷ\�46B\�\��=9ta(6\�6\�\�\��_�!f�~�\�_�\�W\�\�w9�w��\�\��~�\�˶n\��r\�Tm۶\�w�\�r˪�\�;\��\nMc/4\r\�\�ɡ�c}\�ᖴ��~�\�6\��\�\�\�}\�\�H����\�t��\0M\�\�_��\�{\�W5VO>�\�\�ٳg_\�\�\�q�\�4�CӾ�=9���n\�\�\�)�\���{\�砆������,��q�f\�_ztW�\�\�U�]�\�W,U/\�\����~Nww�a\�\�5K�Z��[G�Y�\��۷o\�1Ts�cǎ\�/�\�b��i�it����{2b�i\�\�S�\�\�W\��\�@��nݺ�\�\Z!\0\'����\�\�{jgF�)��\�M+��\�թ�rmf��3zz�2V1\�\�NI?�X��[\�|��T}�ك\�s\�=��M�6��\�Al�\�7�YY�h��2�ƃ>�\�\�\�3m\�߮ʌ\\h��yJǖ��u���.?^�����8j6ξ`vr\�%�\��b\�~\�\�\�Y]�\'\�\\t\�n\�G\����\�\�]\�Ƕ;o�&�����h^\��(�Jى\��r�x<7~F�K=a\�ҥ�R݈�Á�{��\�\�6i\\�z(\��>�4\00N\�\�R�T=�\�S���y�o��\\;.f�\n�zO�\�.�Y*U����zu�x�Z+̩��\�S\�,�j\'5�~�+tuvv�\�}�\��\�;^ٛ\�jӦM7M�2�\�\�ں05b!!�\�\'\�����+�J\�m\��\�\�ꫯ\�\�޺uk��׼\�5�n�\�3\�d\�\����\�{\�\'Fl�\�q\�\���=\�\�\�\��븽`^\�]1\�3\����ۥ��kn\����\�R�/�׋�x�\�x(�;�W7L�\�l�	\0\�/\�{�(�T�P(W/˖\��T����\��7J��\��\�1���\�m\���r\�_\�&{r��\�\�/�|\�ƍ7r�\�\�|;ε�\�\�y#yNS��#�<2��\��}�6�n�\�#�$\�?�|\�F �����\�{l��\�g�}\�s�Kn���\�\�nKn�\�\�MozS�ַ�5{~����o}k�\�\����\� ��\�9\�\�\�x#$��=\rAQ\Z�Ѳ\�Ȯreb��\�ȑ�����m�\�\0\�G�4���1S\�U�~&.�MO\Z�\n\�R\�\��\rPW�:+R\�yP�\�I�ns�&{rw\'N|c{{{O\Z6l߾}K]O���\��&}le\�\�\�\�\�\�xK���M\\b樥o\�}\�{_r��\�\Z�V�\\�<�\�S\�\�1\"\�\�\�4\��\�O\��C�u�\�=h(�۵k\�f\�ӟ�t��H^=o_��cYߛ\'3\���B����\�q�b\�E��#\rV����\�[�\����#�6�\�,W\�\0�/4�+cy^\\\�!m���\�\"\�LR\�,=V�Ns4G��w�˵Sg�T?Y(W��D�4V�M\�	\�bq\�\�ի\�mٲe\�%�\\�h\�\���ێ\�LS�|�\\.g��\�Gw���\"\�x㍻�\�\�)n#D�_��\�R�h \��k��&��:\�3�٦���Z��̞=;{NOOOv�/׋\�\����\�\Z�\�i\Z\�\�s,�7Cv�]]���^�\�L�^���\�_Q3d�ߗ��lֹoV*\�\��?�\�\�R>�	\080\�){�x\�ˋ�Kr\��E3\�\�\�V\�ra�\�\�P��8B\"�\�ҷCyPhF\�\�\��\"@Ej\�=�ۿ�\�\�\�9��/�=��듩S�\�M�ԧ>�۶�7o\���/_�\�~\�;\�\��}_|q\�FÇ\�\�\�9\�K�>����i�\��\�U1\�\�\�l\�n��\��\�\�(\�sʕ�\��\�\�\�\��\�\����\�o�~�\�~q�ɓ\'\�M\�V\0��{|�f�\�\�,�\�EfΜ�}}\�w6\\:�\���1\�BEp\�/\�\��9�LQ�8\�)n#�\�o�\�/$W]uU�ؚ5k�\�U�V\�\nQ#�\����k\��l9^�63�\�u�kg\�,{g\Z�\�\��&Kuz\�ƅcv^ �v\�h�\�\�m\�\�L�p0\�|�\���L\�U=\0�l$BS\\�!�-�s�n�ᆆ�:o~�`\�?\�\�K�\���BS�\�X�7cƌ䵯}m\����\��\�\�\�\���\�\��n�L/��ߡ\�GC��q���\�g�U;g\�\�=6\�\�>��\\]W\�\��\�\�N\�\�wڹL�\����3Rc 4����m:\����7iҤu�;YU\0á)&J�p�_�<_��vS~nQ,��ۿ���ɂN�{#E���\�K�\'�|�\��R1�t\�\�&��\�=�*?�*�0E�����u\�\�\�$�\�Ǆ�����/q^c\\�%.���\�c�)�\��\�n\��\�cYo�T]��;\�\����9ڤ\�Q-5\�\�\�\�ci\�\\�J�,\�x��\0h�\�\�6�P(d�dҤI{\\\"�\�\"0E���@\���\�g\�-\�E!\Z����>:�\�\'>1\�e\�c4�\�\�C\�hZ�7\�Ņ⪚q�\�ᄮQ�ޝ�\��p\�M�78E`�5kV�yL�\�\�ڎS\�\0\0\�xh\Z(H\�\�\�`\��?io\�QG�kVk�K�\"��\�>�ƿ\�\�\�\��B\�3N��T/�\�o�ɲ<\0�����Y�ᦣ>8\�9Nqq�\�{{���\�C�9L1\�@�%{\0@h�b\�)\�CO�����\0ܘEʗ\�\��u\�\�e�\�/�Jəg����y�$/���\n\0 4	M�+q�S��\��q,w\0�\�$41\�\�\�\��\�\�\�\�	K1�\�*y\0\0B�\�D\�hoo��4<\�0t[:�L\�־��kұ(\���\0 4	MB\0\0M�\�\0\0B�!4\0�\�dM\0\0 4B\0\0M�\�\0\0MB\0\0 4	M\0\0�\�$4\0\0B�\�\0\0M�\�\0\0B�!4\0�\�dM\0\0 4B\0\0M�\�\0\0MB\0\0 4	M\0\0�\�$4\0\0B�\�\0\0MB��\0\0�&Ch\0���R�+�glMC\�ˎJ\0\0E�-[�\�\r�Q0֭[wK\Z�uT\0�(R�V?{\�=�lz\�\�.#7\�������t|\�Q	\0\0�Lڨ��dɒ�biX�Sc���Q�	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0h\Z�\��sB�.\0\0\0\0IEND�B`�',1),('3',1,'/home/frank/Workspace/cebrains/ceb-hrc/hrc-admin/target/classes/processes/ExpenseProcess.Expense.png','1','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0M\0\0Y\0\0\0D\�Ĉ\0\0.�IDATx\�\�\���u}?��!*�Zu��e\��~�?�:G[�:آ�R4s\\.\��]0�\�n�AA\�!\�4\n%�ݤQ��P\ZhJ�\�v�$\�OL��� $��	\r&AH�\��yrO\�\\�\�r�w����\�\�\�>{�{�\�\�\�\��lK\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\\N?��\�0\�|x�\0�&M��Ռ�K�\�*\�Z�J������\�\�]s{\�1\�s���\�\�n/�N\0�ƥ\�\�[_W쩝��P�^7��\�P,W�ʵ����޿̂U�y�S\�\�O-�+\�{*\�\�*U��P\'\0\�\0\�V\�0��\�\�i�\����U\�v�M]\�\�\�L���mv�\�\�\�\�j\�^D�\04�K1�T(UO+�\�.=o^\�\�.(׎�Y�¼\�\�\�f�J��\�\�^�=^�\�\nsjU,\�N�\'Q\'\0\�\0\�\���\�\n=�/\�\�˲ey=\�/\�达v\�n��Ti+ί\�*�:\�6\�\�*\��%\�k�\'Q\'\0\�\0\�N\�\�\�\�LQW�����C��4��ڭ��PW�:+R\�yP�\�I�4/B�=�:	\0h���\\�\��\�\"i�\"BԴҲ#c&�P�>3P��\�\�F���byތ�\�\'\�\�Ү�\�ٓ��\0�f���J���\��\��ť\�\�=\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0@�\0̀f\0@�\04\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�T\'@3\0�:	\0h\0\�I\0@3\0�N\0��C\�\��\\�TJ\�Z�n{\0�&\0\�$Ir\�o~󛍩����\�\�\0�\0��f͚\�\'}\�\�ummm�\�+\0 4�z�\��d��\�;�:�f\�Z\�\�\��{\0�&���~��\�I?�6mZ\�\�\�\�c\�\0�\�\�Ԟ~�\�˒,\\�p}Z7O��\0`��E��\�S@�Js\�n۶\�w��;v�T,WL�0\�p{\0\�gh:!/�Z[[O���f�v\�\���!�^�zM[[[�\����\�$4\�\�\�S��\�{\�Ce�d\�֭�D\�\�\�\�8\����N(4��.\�\��կ�7\��\�L�\\r\�\�4��N�\�mz\�,@K\�-�\��\�\�?~�Yy\0h�дǹM\�e\�i\�ĉǤ\�hk��@�loo_\�\�y\0\�$Ґ�\�,@cW^ye9\rI\�\�Cӵ\�^{]Z;\�\�;\0\�<�\�\�u\�2�l�\0�*\�HZ�x���)���:::\�b\�\0@\�\�m2\����\�?�c7nܖ�����\�\�r\0�9CSv%={��\�]*�\�υ�\0S�$��^���x`{�RI�,Y2&G����o\�\�\�M�-[�\�\�\�u�`��\�+���\�~�:	\0\����\�˓\r6$/���1B\��\�O�.]�B\�|\�Q	\�\��\�֭S\'`_\�;�Өi^Z�dɣ�JP\'\ru�Q$��x!=#m^vT�:i��\0�\"�V܋�j\\\�\�IC�`,6/nz6Y�\��\�\�z����:\���\0uR�T\'h�f`�\�u\�\�^�����vq_<\�E\\3\0\�:�N\�\�\�����\��@>\��l�q\�\0��\�:	@s7�־>`3�y\��:�N��\04u3�rI��\�@<\�E\\3\0\�:�N�\�h@�T\'\�I\04�G\\j�f �\"�\0uR�T\'h\�f`ս�l\�1/\�P\'\�Iu��n~�nE��G3�\\r�\��y\��:�N��\04u3\�ɇoأ����k@�T\'\�I\04۶%�\��\�KN\��\�1/\�P\'\�Iu��m\�\�yoy����O�\��:�N��\04_3�m[�\�j�_�/�\�Gl\�z7U3\0\�:�N\�\��P\�z7U3\0\�:�N\�\�\��޼k:ػ�^\�5\�\�[\�\�Iu\0\��/H�\�\�Ë�f`�89I\�-\�:�N�04\�9)\�1�\���D�4\�I\04�f�\�S�b�\�y�I�4\�I\04�f�=\�P��N\Z\�$\0�C3\�4N\"\�;\�I�4\�I�\�K�\�+VL�\����t�\�\�/L�>�厎�\��\�OO&O��\�_�\�K�^z\�\��\�7�\��\�c�C30J\�P�Ȍ�:i��\0\�K�\�~�\rW\\q\�˅B!Y�`A��$\�֭K�nݚ��ݰaC�\��8ICU\�\�յ�X,�����{P3`hF��Z�7�4\�\�Q\'\ruhVi8:\�\��\�\�]t\�EY z\�W���\�#�$_�җ^�2eʣ\'N<\�\�\���L�\�\��6NꤡN4�f�\�\��\�\�瞛,Z�hXa��ŋ\'g�u\��N�C30�Ӂz>ꤡN\�50\�s\�=N�6-�\�/���կ�4�m9\�sγ�5�\��!2\�E�\�A�4\�I`���Lq�ҁ?���ss[[\�d{Y3�\����Ό�:i��\0;\�9L�$\�@\�05�q:\�36��\�8{[3�\� \�����3�n����\\B�4\�I�\�\�U�����\�0Lw\�uז����\�q̀f���N�>\�E�\�\�\�\�4\��\'u\�P\'�f�p\�\���\��\�w�K��S���LO3�\� �pf_�I��\�S>Ψ{\�9NꤡN\�(f�\�s�\�\�\�O�ӗ\�6i4@�Fh�\�\�h�kl\�\'u\�P\'�f�bŊ)q�����p�u\�Y\�O?��w\������mI:�L3�h�\�y��t�0�P�ഷ��p�TO�4\�I�|\�{\�[z\�\�&�Ҝ9sICӅ���K�_#��\�\�\�k4\�p<\�cp*8\r70\���\�[\�8���:	��g\�~\�8��\��\��U�B�\�\�\��f�n\�\�\�Ùf`<\�\r\�\����\�a��r\�qR\'\ruhӧO�\�g�=��i���\��5��mv��Z\�h4\�\�x\�\��4Xp�\�~�����N\Z\�$0Vttt$[�n=��)~_�µ\�\�?�\�@�;��E3�O\�s\��\�\�4\nN���\'\�8���:	�\����/f\�c��\�O~�<���\�pL\��8\�\��p3g?\�\�a\rf�~\�w���\�\�N\ZB0N�y\�;�LӖ-[֛i:h\�>\�\�\�zʄ	�\�FC3p\�e�e�\�ʕ+�\��裏N&M��\�F\�s\�i�m_��\�\'\�w\�^�;��\�$\�|\�;w}?��3\�HV�X��>\��\�?�[��Z\��\�4\r\�p\�4���k\�ʵ��mS�\�{b�\\���T�*n��\�??\������\�(�*m\�}yT\�\�o�1����\�\�߼ys�t\�\�AC_^K_�\����y\�l��\�\�}O<�\���6m\�K�����8�\�9M�\�\�\�\\���\��\�Of/\�?��ϓ�7f/�m�\�\��6��\�{��&\�|\\�~�m\��\�\�c�X�\"�\�\�~7��@\�\�={v\�\�\�p/��®\�\�q\�T5��x�(�[\�9M������\�\�\�\�]\�ڿ�\���#�=�\�7U�\�c\�H�Q��rT\��\�\\�\���;ұ%��1��\��\�iŞڙip:~�\�\�8\��\�/g�妛n\�U\����~��\�\�n\��\�\�v֬Y\�\�~��\��s��\�n\�y�ᇓ�\�;���\�oϞ\�}ӛޔ���o\�jm�Ϸ���ݾ\�{\�#4���\\u����\���\�]=\�5������:@s9b\�@�E�e_\�;�yh\�\�\�l��X��\���������\�SO=5\�\�Q�s\�6�ϛ��\�\�}�}\�k�p3^�݉\'����hH4c\�xnT�vfg�ˊ7�z\�p�SS_=\�r\�44�=p�\�>,�)�*���\�?�!\�#d��\�#�\�\�\�\�/6zn�]O%�h�l��T�n��w�wͫ�+f��\�\�s��3\��u2\��Po�\�Eq߃>������E�]<���\r�<4U*�=PԸ��\�ah�7�\�Gy$�]�vm�>�\�Og_M@ӻ�\�+�=ԟ\�4s\�̻|N\�~7���?⡩�8f����\�с�3FӧO϶�\�?�ӆ\�\�x�PH\���7$�^ziv\�7��8�v5�ۯZ�*����\'y�ߜ\�t\�s���\�䪫�\��4��x 8\rX�����\0\��Nhi\�\�i\�*W?��r\�\�bO\�\�\�)�����ko��ϛ\���Hi��B,\�\�o��9s�J��]:�ExJƂ��*\�\��\��\�\'\�_���\�\�_\��=\�iX�v��\�\�c��N\�5)�ֵ�-]�o\�\�\�F�\�y�7�\���\�\�ncyq\�F�����_\�\\sM����=[��M�v�V\�~g^\'\�6�+�\��f�\�I�)|\�+_�\�\���W^y\�]<oҤIO�/f\����\nMq�x�=�\�s��_\�r�Xv2\��x��\�!\�ϻ\���\�\�/Ξw\�9\�4\�>O�N?��\�p\�\r\�;��ꗵ\�\�\�o\'O���X�fͮY�h4c\�P3={���\�\rN��S�m�G�\�S��ΠR����\�\�e�T9?�\�5Y�\�|��\��>R(\�\�w�\����\���z\�@�\��\�N˖\�*WŒ�\�Z\'# \��Lq�\�yCQ�b�\\<�\��\�R\�|��U�\�z|u.~�F�\�1s\�ҷ\\9\���:u\�7�>��O\�qnT��/_�\�\�yQ�\�9�\�$0\�͘1\����Ǉjiލi`Zn��\�\�/��\�[�</\�]��\�h9\\��=�]�����\�\�\�\�b�(\�u\�Ot��.q[.�wm�;���\\�Xj��c_\�K��/m\��)\�8\�m`\Znp\�_\���\�0\�e\�R^\r0;o�:��A �.��*W\�M��W���r�u��\�\�i�eGK�+��\�o\�\�R\�{G[��\�+f�\�wG��Y�\�\��x�\Z]���\�\�5�x윣���������Y��˩�Y��\�y�\�C�s�\0��}�\�/�\����l\�\�I�&����m��>�CS��e��<�Dx\�T�\�D\�y\��޷\�\�8�)\��7���Nm,\��\�g?�-O��u�\�7\\�W\�@Ļ��L\�G?�\�]K]\Z5)��1%f��/�;�e��\�\�(8�\�\��4�\�\��H}d�\�\�l���[c݇\�\�~2�yk\�B\�R��4[�W��K�\"�\rtv���\�_\rS\�\�WӲ#c\�\�hMQ�b�^�FRK�evyX\�\�P�\�G>�\��x^,enb�<�\�\�Ҽ3fd�R�����^-u�\�\�R�)�#~Wlo,���N�ڔ)SVD1=�\����e\Z\�y��Д/\r�w)�s�\�Ν��F�)�\��w�c���Q�wS\�\�{����\'6ǒ���\�g\�\�6�w^��H��c�9f��	�\�\��5\�\"8\�_�>\0\r\�kz����]\�a�ˍ\���pC�T�\�\��4$m\�j^�\�TK\�\�\��87*��󝊥\�\�q1��\��ү�b�[�vv���J��\�X�7�\�d���\�\��Y�>�OdK�\�\�7��%\�w\�u׮�\�\�\�S,��\�\����&ұ\��\�`\rvY�|\�*�7\�m���ʧq��������@Ӌs�\�:\�-��\�/J`Z�bŝ\�\�x���\�8{{���+��b\�\��\�\��Mxb\�H��\�x\�4Ų�8*\Z�\�>.�P���$\�N�h���x�6\r\�}\�H4?�яv-\�$�\�/L�(\���\��^�3Z���\�\�\�χjZ�9\�ޙ_ B�\�9\"\�y��u~!��1�\�*ň����3ތ:ꨣ�7��\�F�Al����\�_\�C�\�y\0}&O�\�>eʔ\�nذ\���k\��ǤI���,�yB\�`\�@���\�\��wV30\�\��j��uřZ\�\�&\��<TWU\'�q�����\�\�\�\�j\�)f�\"0���~\�\�\�4�\���\�Դ\\�N\Z\�$�Ҁs�\�ɓ_�\�;^܏�Cl����\�cI^::\�Ù�8\��wI�%yꤡN.\�q�4iReʔ)\��l\�\�0�\�G?�9\r]\�E�ä04� 8\rw�\�E\�IC�Vx�|�����\r�g\��\��\�<�nݺg㼔�e˖�\�<�\�/z{{�����\����\��bv\�U�4�f`\�;K�\�IC�\�7i�����B\Z�nKǓ\�\�\Z�!\�]��E\�0f�\�-̀���\�tlni\��U\'\�\'u\0̀�\�\�@t� �?/0���:	�f�\�4��f�,\�S\'\ru\0̀�`��$0��\�:	�f���f��\��	uR�@3\�EX3@c\'\�\�lZ\\V\\�T\'\�I\04^�54\��P\'\�\'u\0̀a\�\0B\�:	\0�\�\0B\�:	\0�\�\0B\�:	\0�\�\0B\�:	\0�\�\0BꤡN�04BꤡN�04BꤡN�04BꤡN�04BꤡN�04M��\�$\0h4M��\�$\0h4M��\�$\0h4M��\�$\0h4M���:	�f@3��P\'\ru�Q�R�x=ck\��\��P\'\ru�Qdٲe\�nذ��(\�֭�%muT\nM���:	�(R�V?{\�=�lz\�\�xA�wN�\�\�\�}*�wT\nM��)p��\0��\��S�,Y�P,y��\�cm\�|�\�I4�c�\�\�7b�?��P\'\�x���R��,\\�P��&mlEs;a\�\�\r�&\�]�$G�\�7�٘J:;;�\��&\�\�\��ᴱ}9�۶���\��&\�ݚ5k����\��֥��T{\0�+4-�ƶo<d� 4��^|�\�?پ}���άY�\�ttt�\�\��\�hhO\�g�򑆨S\��&\�i�����~6mڴ����\�\��\�hh\��|�ɹMM\�\���\�O_�`\�\�\�\'{	\0\�w3�\�,S>�\�Gh�٥�\��m\����Bӎ;^*�+�\�\0㻙��Q`\��\�CM4��k\�ޟa�\�\�k\�\�\�\n�\0�\�Fv�Y&\�6!4AK˽�\�;TfJ�n\�zO\�G\�c\00�\Z\�E�&W\�Ch�\�}��_=o�\�y��.�\��i\0\�-8�hZ�\�r\�/JL�?��5��\�9M\0��\�Mk\�ĉǤ\�hk��@�loo_\�\�y\0��\�M\�\�+�,�!i{}h��\�k�kmm�c\�\0�\�W4�X~�x�\�\rul{Sz\\�\�\�\�x��\0�[p\\A\���\��\�ƍ��#\�\�\�\��Yv\0��\���`���K�R|\�B{\04�ข~b9^\�qq{\04�\�\�3\0��q�g\0@3�\�\n\�\0�f\0\�8�\0\�\0�+p<\0��\�\�\�\�>̞A�\040�qU\�]Z(\��p\�¼\�\�\�e]�\�Uq[,������v\�\�}G�Ti+����ˡN\0�F\�*���Ϙ��衞\�}}\�bO\��\rCU��X:�|������5���tl�mf\�\�=v��\�CZ��vf\Z���WC�\04�\�qU(WK��\n=\�]�\��=\�Rw�a�k\�J�BWO��Ґ�]\�ڿ��\�#�\�\�\�\�/6�}\�v=���c��T�n��\�]0��iJC\�7�\�$\0�\��W\�\�\�}[�\\�\�Ji`YR쩝<����\��~/\�6������/\�r��v�`4���t�ߥc[��a\�ݵËsz�8���WT�3Ş\�?t|���Ŀ��\���͍\��\�P\'\0\�\0#v\\u�*\�\�L\�\���7�ܮRuVw���l���C\�G\n�Z�\�Q�>l\��\�\���\�﫧eK�J��bI��\Z\�$\0�`D��i�eG\�lN��\Z\��\�\�_�bV�\\97�z^��ʵց\�\��M\�R\�,��kg\��\�i�\����N\0�\�qK\�\"�\�\��J��#@\�/\�\�#4�j���\�ܦ=\�O��hM���\� ��K��zq\�T��D\�T\rtv���\�_\rS\�\�\�^u�� \�$\0�\�Wq��+�O\��ĹD�/�K\�\�\�\r\�Rev�����M}W͋s�ji��`ܟ\�`�\�\��N\�R�Ḙ\�@�\�}\�\�Q�sc�)�\�\�W�?]zu\0\�0j��e\�ܛ�@�\048���\0hp\\�\�\0\�\�\�3\0�\0\��g\0@3\0�+\�\0�f\0W\�x\048���\0hp\\�\�\0\�\�\�3\0��q�g\0@3�\�\n\�\0�f\0\�8�\0\�\0�+\��g\0@3\0�+\�--\�ݵ\�\�%\0\�܂\�\n\�s�P��\Z�̫��X�N-�\�\�,����\�I\�;�kn\����\�R�/\n�zO�7@s�+\���\�U��\�\�ޟ�4z*_h��i�eGv�+\�7c�ң����G@s�+\����L��\�b�vR�T]\���X�׹`\�\�{��i��0��\�R\�⮹\�٣\0��\�\�\�xvD6\�T�.��\n\�\�\��\�\��H���\�g�T?��Jq~\�|V*=	\0�[p\\1��\�b��ϧ�\�\�7�\��\�b�rnWO�\�\�=\�J�o�N\�.e\�)W�v\�s+�\�I\0\�܂\�q}<\�9L\�r�rmf,�\�*\�\�.\�Y�\�4 \�\����Ruzܞ7��mq��yWؓ\0��\�\��x.�*m1�\�\�f\�\�=6\�\�K�\�_\�\nK�j�d\�;�*�U;/Q;;f�\�I\0\�܂\�q{<\�_��kn\��J\�Y\�R\�\�4]3M�dog�zu���ko��\n�\�\�tܑ�\�\�\�({\04�ย)�\�\�CW���\�\�[_7\�\�p�q\0\�\�\�\�\�Т8��\�S��\0�f�f=�NH\�˃��\�\�\�S\�)\�I\0@3@3Gw�\�0a\�\��\�$\0�����\nMmmm�\�C��\0�f\0\�R\�s�2˄:	\0h���M\�eB�\04P\'\rI\�2�N\0�84}�\�\\��\�\�I\0@3\0{SٹMf�P\'\0\�\04>�Np\\�N\0��$I_�z�m<���J��,Y�\�8ģ��7Y�l\��c�#u\0\��2��/_�lذ!y饗�\Z\�?�|�t\�\�\�\0�yG%\�$\0h4�H\�0	L�&8��dɒG���\0�\��\"�$O`=#\rM/;*Q\'@3�E\�\ZaeT�&��N�f@30CӋ��MV?t}�ޯe#����	u\0\�4}hڼq]��%?�\�\�F\��	;B\�$\0�h\�д\�g��G`\�\�ڟ-v�&\�I\0@3\�ܡ\�\�\�\�M�#4�N\0���M+�t�\�1aGhB�\04B�\�$4�N\0����\�\r�\�1aGhB�\04M�V\�;o�\��	;B\�$\0�h\�\��\�u+��?��\�Ҽ��xL\��P\'\0\�@S��O>|\��)\�t�&\�I\0@3 4mۖ��\�{.\�K\�Ǆ�	u\0\�4mhڼq]�\�{\��\��\�6�\�\�>\�\�EQ�\�S\0 41\�BӶmɳ��\�-�d����\�&�5\�$4�O5�t�<Xhjmm=Ş\0��Q���]2\�$4q�\�\�݃���\'L�p��\0B�(4\�\�\�\�`�N\�İ\�\�	�����S\�!\0�e�i_S>���}���\�mz\�,\0M�\�\�dM�Hm\�\�\�&\�2�Є\�dM\�IC\�B�L\0�\�$4B��ם\�t�=\0BB�!4�g�\�\�m2\�\0BB�!4�joo��\�\�\��\�\�x[:V�\�}�M[\��dߒ�c;{\0�&�&�Ihj\Zq)�t,I\�K�}�m�QK��l\��Є\�$41�k\�\�\�xA�\�X>q\�\�c\�M\0���&ƕ\�\�\�\�\'M����\0\�\�ޞ��\��}R�V���~:�����$����M֭[�\�{\�ɜ9s�/|\���\�f�N\0 4!4	M���tJ\Zr^�K7\�xc�aÆdolܸ1\�>�W�ҟy��\0BB�\�\�XL�\��3fd�J�\"�w\�E�Nf�\0@hBh��5\�\��K�fΜ�k	޾��{�f\�\�T\�8{\0�&�&��1\'\rL��3L��\�S��\��6\0MMBcJ\\V���}]�7\�R��s�,\�\0��}0aÅ�����{2\�BӒ<\�\�E��o�\�l\0M싴��õk\�\��+�\�W�r�@Ͷ\�46B\�\��=9ta(6\�6\�\�\��_�!f�~�\�_�\�W\�\�w9�w��\�\��~�\�˶n\��r\�Tm۶\�w�\�r˪�\�;\��\nMc/4\r\�\�ɡ�c}\�ᖴ��~�\�6\��\�\�\�}\�\�H����\�t��\0M\�\�_��\�{\�W5VO>�\�\�ٳg_\�\�\�q�\�4�CӾ�=9���n\�\�\�)�\���{\�砆������,��q�f\�_ztW�\�\�U�]�\�W,U/\�\����~Nww�a\�\�5K�Z��[G�Y�\��۷o\�1Ts�cǎ\�/�\�b��i�it����{2b�i\�\�S�\�\�W\��\�@��nݺ�\�\Z!\0\'����\�\�{jgF�)��\�M+��\�թ�rmf��3zz�2V1\�\�NI?�X��[\�|��T}�ك\�s\�=��M�6��\�Al�\�7�YY�h��2�ƃ>�\�\�\�3m\�߮ʌ\\h��yJǖ��u���.?^�����8j6ξ`vr\�%�\��b\�~\�\�\�Y]�\'\�\\t\�n\�G\����\�\�]\�Ƕ;o�&�����h^\��(�Jى\��r�x<7~F�K=a\�ҥ�R݈�Á�{��\�\�6i\\�z(\��>�4\00N\�\�R�T=�\�S���y�o��\\;.f�\n�zO�\�.�Y*U����zu�x�Z+̩��\�S\�,�j\'5�~�+tuvv�\�}�\��\�;^ٛ\�jӦM7M�2�\�\�ں05b!!�\�\'\�����+�J\�m\��\�\�ꫯ\�\�޺uk��׼\�5�n�\�3\�d\�\����\�{\�\'Fl�\�q\�\���=\�\�\�\��븽`^\�]1\�3\����ۥ��kn\����\�R�/�׋�x�\�x(�;�W7L�\�l�	\0\�/\�{�(�T�P(W/˖\��T����\��7J��\��\�1���\�m\���r\�_\�&{r��\�\�/�|\�ƍ7r�\�\�|;ε�\�\�y#yNS��#�<2��\��}�6�n�\�#�$\�?�|\�F �����\�{l��\�g�}\�s�Kn���\�\�nKn�\�\�MozS�ַ�5{~����o}k�\�\����\� ��\�9\�\�\�x#$��=\rAQ\Z�Ѳ\�Ȯreb��\�ȑ�����m�\�\0\�G�4���1S\�U�~&.�MO\Z�\n\�R\�\��\rPW�:+R\�yP�\�I�ns�&{rw\'N|c{{{O\Z6l߾}K]O���\��&}le\�\�\�\�\�\�xK���M\\b樥o\�}\�{_r��\�\Z�V�\\�<�\�S\�\�1\"\�\�\�4\��\�O\��C�u�\�=h(�۵k\�f\�ӟ�t��H^=o_��cYߛ\'3\���B����\�q�b\�E��#\rV����\�[�\����#�6�\�,W\�\0�/4�+cy^\\\�!m���\�\"\�LR\�,=V�Ns4G��w�˵Sg�T?Y(W��D�4V�M\�	\�bq\�\�ի\�mٲe\�%�\\�h\�\���ێ\�LS�|�\\.g��\�Gw���\"\�x㍻�\�\�)n#D�_��\�R�h \��k��&��:\�3�٦���Z��̞=;{NOOOv�/׋\�\����\�\Z�\�i\Z\�\�s,�7Cv�]]���^�\�L�^���\�_Q3d�ߗ��lֹoV*\�\��?�\�\�R>�	\080\�){�x\�ˋ�Kr\��E3\�\�\�V\�ra�\�\�P��8B\"�\�ҷCyPhF\�\�\��\"@Ej\�=�ۿ�\�\�\�9��/�=��듩S�\�M�ԧ>�۶�7o\���/_�\�~\�;\�\��}_|q\�FÇ\�\�\�9\�K�>����i�\��\�U1\�\�\�l\�n��\��\�\�(\�sʕ�\��\�\�\�\��\�\����\�o�~�\�~q�ɓ\'\�M\�V\0��{|�f�\�\�,�\�EfΜ�}}\�w6\\:�\���1\�BEp\�/\�\��9�LQ�8\�)n#�\�o�\�/$W]uU�ؚ5k�\�U�V\�\nQ#�\����k\��l9^�63�\�u�kg\�,{g\Z�\�\��&Kuz\�ƅcv^ �v\�h�\�\�m\�\�L�p0\�|�\���L\�U=\0�l$BS\\�!�-�s�n�ᆆ�:o~�`\�?\�\�K�\���BS�\�X�7cƌ䵯}m\����\��\�\�\�\���\�\��n�L/��ߡ\�GC��q���\�g�U;g\�\�=6\�\�>��\\]W\�\��\�\�N\�\�wڹL�\����3Rc 4����m:\����7iҤu�;YU\0á)&J�p�_�<_��vS~nQ,��ۿ���ɂN�{#E���\�K�\'�|�\��R1�t\�\�&��\�=�*?�*�0E�����u\�\�\�$�\�Ǆ�����/q^c\\�%.���\�c�)�\��\�n\��\�cYo�T]��;\�\����9ڤ\�Q-5\�\�\�\�ci\�\\�J�,\�x��\0h�\�\�6�P(d�dҤI{\\\"�\�\"0E���@\���\�g\�-\�E!\Z����>:�\�\'>1\�e\�c4�\�\�C\�hZ�7\�Ņ⪚q�\�ᄮQ�ޝ�\��p\�M�78E`�5kV�yL�\�\�ڎS\�\0\0\�xh\Z(H\�\�\�`\��?io\�QG�kVk�K�\"��\�>�ƿ\�\�\�\��B\�3N��T/�\�o�ɲ<\0�����Y�ᦣ>8\�9Nqq�\�{{���\�C�9L1\�@�%{\0@h�b\�)\�CO�����\0ܘEʗ\�\��u\�\�e�\�/�Jəg����y�$/���\n\0 4	M�+q�S��\��q,w\0�\�$41\�\�\�\��\�\�\�\�	K1�\�*y\0\0B�\�D\�hoo��4<\�0t[:�L\�־��kұ(\���\0 4	MB\0\0M�\�\0\0B�!4\0�\�dM\0\0 4B\0\0M�\�\0\0MB\0\0 4	M\0\0�\�$4\0\0B�\�\0\0M�\�\0\0B�!4\0�\�dM\0\0 4B\0\0M�\�\0\0MB\0\0 4	M\0\0�\�$4\0\0B�\�\0\0MB��\0\0�&Ch\0���R�+�glMC\�ˎJ\0\0E�-[�\�\r�Q0֭[wK\Z�uT\0�(R�V?{\�=�lz\�\�.#7\�������t|\�Q	\0\0�Lڨ��dɒ�biX�Sc���Q�	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0h\Z�\��sB�.\0\0\0\0IEND�B`�',1),('5002',1,'/home/frank/Workspace/cebrains/ceb-hrc/hrc-admin/target/classes/processes/ExpenseProcess.bpmn20.xml','5001','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n             xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\"\n             xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\"\n             typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\"\n             targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"Expense\" name=\"ExpenseProcess\" isExecutable=\"true\">\n    <documentation>报销流程</documentation>\n    <startEvent id=\"start\" name=\"开始\n\"></startEvent>\n    <userTask id=\"fillTask\" name=\"出差报销\" flowable:assignee=\"${taskUser}\">\n      <extensionElements>\n        <modeler:initiator-can-complete xmlns:modeler=\"http://flowable.org/modeler\"><![CDATA[false]]></modeler:initiator-can-complete>\n      </extensionElements>\n    </userTask>\n    <exclusiveGateway id=\"judgeTask\"></exclusiveGateway>\n    <userTask id=\"directorTak\" name=\"经理审批\">\n      <extensionElements>\n        <flowable:taskListener event=\"create\" class=\"com.cebrains.hrc.modular.flowable.handler.ManagerTaskHandler\"></flowable:taskListener>\n      </extensionElements>\n    </userTask>\n    <userTask id=\"bossTask\" name=\"老板审批\">\n      <extensionElements>\n        <flowable:taskListener event=\"create\" class=\"com.cebrains.hrc.modular.flowable.handler.BossTaskHandler\"></flowable:taskListener>\n      </extensionElements>\n    </userTask>\n    <endEvent id=\"end\" name=\"结束\"></endEvent>\n    <sequenceFlow id=\"directorNotPassFlow\" name=\"驳回\" sourceRef=\"directorTak\" targetRef=\"fillTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'驳回\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"bossNotPassFlow\" name=\"驳回\" sourceRef=\"bossTask\" targetRef=\"fillTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'驳回\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow1\" sourceRef=\"start\" targetRef=\"fillTask\"></sequenceFlow>\n    <sequenceFlow id=\"flow2\" sourceRef=\"fillTask\" targetRef=\"judgeTask\"></sequenceFlow>\n    <sequenceFlow id=\"judgeMore\" name=\"大于500元\" sourceRef=\"judgeTask\" targetRef=\"bossTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money > 500}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"bossPassFlow\" name=\"通过\" sourceRef=\"bossTask\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'通过\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"directorPassFlow\" name=\"通过\" sourceRef=\"directorTak\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'通过\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"judgeLess\" name=\"小于500元\" sourceRef=\"judgeTask\" targetRef=\"directorTak\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money <= 500}]]></conditionExpression>\n    </sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_Expense\">\n    <bpmndi:BPMNPlane bpmnElement=\"Expense\" id=\"BPMNPlane_Expense\">\n      <bpmndi:BPMNShape bpmnElement=\"start\" id=\"BPMNShape_start\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"285.0\" y=\"135.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"fillTask\" id=\"BPMNShape_fillTask\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"405.0\" y=\"110.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"judgeTask\" id=\"BPMNShape_judgeTask\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"585.0\" y=\"130.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"directorTak\" id=\"BPMNShape_directorTak\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"735.0\" y=\"110.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"bossTask\" id=\"BPMNShape_bossTask\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"555.0\" y=\"255.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"end\" id=\"BPMNShape_end\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"771.0\" y=\"281.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">\n        <omgdi:waypoint x=\"315.0\" y=\"150.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"405.0\" y=\"150.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">\n        <omgdi:waypoint x=\"505.0\" y=\"150.16611295681062\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"585.4333333333333\" y=\"150.43333333333334\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"judgeLess\" id=\"BPMNEdge_judgeLess\">\n        <omgdi:waypoint x=\"624.5530726256983\" y=\"150.44692737430168\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"735.0\" y=\"150.1392757660167\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"directorNotPassFlow\" id=\"BPMNEdge_directorNotPassFlow\">\n        <omgdi:waypoint x=\"785.0\" y=\"110.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"37.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"37.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"110.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"bossPassFlow\" id=\"BPMNEdge_bossPassFlow\">\n        <omgdi:waypoint x=\"655.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"771.0\" y=\"295.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"judgeMore\" id=\"BPMNEdge_judgeMore\">\n        <omgdi:waypoint x=\"605.4340277777778\" y=\"169.56597222222223\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"605.1384083044983\" y=\"255.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"directorPassFlow\" id=\"BPMNEdge_directorPassFlow\">\n        <omgdi:waypoint x=\"785.0\" y=\"190.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"281.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"bossNotPassFlow\" id=\"BPMNEdge_bossNotPassFlow\">\n        <omgdi:waypoint x=\"555.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"190.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('5003',1,'/home/frank/Workspace/cebrains/ceb-hrc/hrc-admin/target/classes/processes/ExpenseProcess.Expense.png','5001','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0M\0\0Y\0\0\0D\�Ĉ\0\0.�IDATx\�\�\���u}?��!*�Zu��e\��~�?�:G[�:آ�R4s\\.\��]0�\�n�AA\�!\�4\n%�ݤQ��P\ZhJ�\�v�$\�OL��� $��	\r&AH�\��yrO\�\\�\�r�w����\�\�\�>{�{�\�\�\�\��lK\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\\N?��\�0\�|x�\0�&M��Ռ�K�\�*\�Z�J������\�\�]s{\�1\�s���\�\�n/�N\0�ƥ\�\�[_W쩝��P�^7��\�P,W�ʵ����޿̂U�y�S\�\�O-�+\�{*\�\�*U��P\'\0\�\0\�V\�0��\�\�i�\����U\�v�M]\�\�\�L���mv�\�\�\�\�j\�^D�\04�K1�T(UO+�\�.=o^\�\�.(׎�Y�¼\�\�\�f�J��\�\�^�=^�\�\nsjU,\�N�\'Q\'\0\�\0\�\���\�\n=�/\�\�˲ey=\�/\�达v\�n��Ti+ί\�*�:\�6\�\�*\��%\�k�\'Q\'\0\�\0\�N\�\�\�\�LQW�����C��4��ڭ��PW�:+R\�yP�\�I�4/B�=�:	\0h���\\�\��\�\"i�\"BԴҲ#c&�P�>3P��\�\�F���byތ�\�\'\�\�Ү�\�ٓ��\0�f���J���\��\��ť\�\�=\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0@�\0̀f\0@�\04\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�T\'@3\0�:	\0h\0\�I\0@3\0�N\0��C\�\��\\�TJ\�Z�n{\0�&\0\�$Ir\�o~󛍩����\�\�\0�\0��f͚\�\'}\�\�ummm�\�+\0 4�z�\��d��\�;�:�f\�Z\�\�\��{\0�&���~��\�I?�6mZ\�\�\�\�c\�\0�\�\�Ԟ~�\�˒,\\�p}Z7O��\0`��E��\�S@�Js\�n۶\�w��;v�T,WL�0\�p{\0\�gh:!/�Z[[O���f�v\�\���!�^�zM[[[�\����\�$4\�\�\�S��\�{\�Ce�d\�֭�D\�\�\�\�8\����N(4��.\�\��կ�7\��\�L�\\r\�\�4��N�\�mz\�,@K\�-�\��\�\�?~�Yy\0h�дǹM\�e\�i\�ĉǤ\�hk��@�loo_\�\�y\0\�$Ґ�\�,@cW^ye9\rI\�\�Cӵ\�^{]Z;\�\�;\0\�<�\�\�u\�2�l�\0�*\�HZ�x���)���:::\�b\�\0@\�\�m2\����\�?�c7nܖ�����\�\�r\0�9CSv%={��\�]*�\�υ�\0S�$��^���x`{�RI�,Y2&G����o\�\�\�M�-[�\�\�\�u�`��\�+���\�~�:	\0\����\�˓\r6$/���1B\��\�O�.]�B\�|\�Q	\�\��\�֭S\'`_\�;�Өi^Z�dɣ�JP\'\ru�Q$��x!=#m^vT�:i��\0�\"�V܋�j\\\�\�IC�`,6/nz6Y�\��\�\�z����:\���\0uR�T\'h�f`�\�u\�\�^�����vq_<\�E\\3\0\�:�N\�\�\�����\��@>\��l�q\�\0��\�:	@s7�־>`3�y\��:�N��\04u3�rI��\�@<\�E\\3\0\�:�N�\�h@�T\'\�I\04�G\\j�f �\"�\0uR�T\'h\�f`ս�l\�1/\�P\'\�Iu��n~�nE��G3�\\r�\��y\��:�N��\04u3\�ɇoأ����k@�T\'\�I\04۶%�\��\�KN\��\�1/\�P\'\�Iu��m\�\�yoy����O�\��:�N��\04_3�m[�\�j�_�/�\�Gl\�z7U3\0\�:�N\�\��P\�z7U3\0\�:�N\�\�\��޼k:ػ�^\�5\�\�[\�\�Iu\0\��/H�\�\�Ë�f`�89I\�-\�:�N�04\�9)\�1�\���D�4\�I\04�f�\�S�b�\�y�I�4\�I\04�f�=\�P��N\Z\�$\0�C3\�4N\"\�;\�I�4\�I�\�K�\�+VL�\����t�\�\�/L�>�厎�\��\�OO&O��\�_�\�K�^z\�\��\�7�\��\�c�C30J\�P�Ȍ�:i��\0\�K�\�~�\rW\\q\�˅B!Y�`A��$\�֭K�nݚ��ݰaC�\��8ICU\�\�յ�X,�����{P3`hF��Z�7�4\�\�Q\'\ruhVi8:\�\��\�\�]t\�EY z\�W���\�#�$_�җ^�2eʣ\'N<\�\�\���L�\�\��6NꤡN4�f�\�\��\�\�瞛,Z�hXa��ŋ\'g�u\��N�C30�Ӂz>ꤡN\�50\�s\�=N�6-�\�/���կ�4�m9\�sγ�5�\��!2\�E�\�A�4\�I`���Lq�ҁ?���ss[[\�d{Y3�\����Ό�:i��\0;\�9L�$\�@\�05�q:\�36��\�8{[3�\� \�����3�n����\\B�4\�I�\�\�U�����\�0Lw\�uז����\�q̀f���N�>\�E�\�\�\�\�4\��\'u\�P\'�f�p\�\���\��\�w�K��S���LO3�\� �pf_�I��\�S>Ψ{\�9NꤡN\�(f�\�s�\�\�\�O�ӗ\�6i4@�Fh�\�\�h�kl\�\'u\�P\'�f�bŊ)q�����p�u\�Y\�O?��w\������mI:�L3�h�\�y��t�0�P�ഷ��p�TO�4\�I�|\�{\�[z\�\�&�Ҝ9sICӅ���K�_#��\�\�\�k4\�p<\�cp*8\r70\���\�[\�8���:	��g\�~\�8��\��\��U�B�\�\�\��f�n\�\�\�Ùf`<\�\r\�\����\�a��r\�qR\'\ruhӧO�\�g�=��i���\��5��mv��Z\�h4\�\�x\�\��4Xp�\�~�����N\Z\�$0Vttt$[�n=��)~_�µ\�\�?�\�@�;��E3�O\�s\��\�\�4\nN���\'\�8���:	�\����/f\�c��\�O~�<���\�pL\��8\�\��p3g?\�\�a\rf�~\�w���\�\�N\ZB0N�y\�;�LӖ-[֛i:h\�>\�\�\�zʄ	�\�FC3p\�e�e�\�ʕ+�\��裏N&M��\�F\�s\�i�m_��\�\'\�w\�^�;��\�$\�|\�;w}?��3\�HV�X��>\��\�?�[��Z\��\�4\r\�p\�4���k\�ʵ��mS�\�{b�\\���T�*n��\�??\������\�(�*m\�}yT\�\�o�1����\�\�߼ys�t\�\�AC_^K_�\����y\�l��\�\�}O<�\���6m\�K�����8�\�9M�\�\�\�\\���\��\�Of/\�?��ϓ�7f/�m�\�\��6��\�{��&\�|\\�~�m\��\�\�c�X�\"�\�\�~7��@\�\�={v\�\�\�p/��®\�\�q\�T5��x�(�[\�9M������\�\�\�\�]\�ڿ�\���#�=�\�7U�\�c\�H�Q��rT\��\�\\�\���;ұ%��1��\��\�iŞڙip:~�\�\�8\��\�/g�妛n\�U\����~��\�\�n\��\�\�v֬Y\�\�~��\��s��\�n\�y�ᇓ�\�;���\�oϞ\�}ӛޔ���o\�jm�Ϸ���ݾ\�{\�#4���\\u����\���\�]=\�5������:@s9b\�@�E�e_\�;�yh\�\�\�l��X��\���������\�SO=5\�\�Q�s\�6�ϛ��\�\�}�}\�k�p3^�݉\'����hH4c\�xnT�vfg�ˊ7�z\�p�SS_=\�r\�44�=p�\�>,�)�*���\�?�!\�#d��\�#�\�\�\�\�/6zn�]O%�h�l��T�n��w�wͫ�+f��\�\�s��3\��u2\��Po�\�Eq߃>������E�]<���\r�<4U*�=PԸ��\�ah�7�\�Gy$�]�vm�>�\�Og_M@ӻ�\�+�=ԟ\�4s\�̻|N\�~7���?⡩�8f����\�с�3FӧO϶�\�?�ӆ\�\�x�PH\���7$�^ziv\�7��8�v5�ۯZ�*����\'y�ߜ\�t\�s���\�䪫�\��4��x 8\rX�����\0\��Nhi\�\�i\�*W?��r\�\�bO\�\�\�)�����ko��ϛ\���Hi��B,\�\�o��9s�J��]:�ExJƂ��*\�\��\��\�\'\�_���\�\�_\��=\�iX�v��\�\�c��N\�5)�ֵ�-]�o\�\�\�F�\�y�7�\���\�\�ncyq\�F�����_\�\\sM����=[��M�v�V\�~g^\'\�6�+�\��f�\�I�)|\�+_�\�\���W^y\�]<oҤIO�/f\����\nMq�x�=�\�s��_\�r�Xv2\��x��\�!\�ϻ\���\�\�/Ξw\�9\�4\�>O�N?��\�p\�\r\�;��ꗵ\�\�\�o\'O���X�fͮY�h4c\�P3={���\�\rN��S�m�G�\�S��ΠR����\�\�e�T9?�\�5Y�\�|��\��>R(\�\�w�\����\���z\�@�\��\�N˖\�*WŒ�\�Z\'# \��Lq�\�yCQ�b�\\<�\��\�R\�|��U�\�z|u.~�F�\�1s\�ҷ\\9\���:u\�7�>��O\�qnT��/_�\�\�yQ�\�9�\�$0\�͘1\����Ǉjiލi`Zn��\�\�/��\�[�</\�]��\�h9\\��=�]�����\�\�\�\�b�(\�u\�Ot��.q[.�wm�;���\\�Xj��c_\�K��/m\��)\�8\�m`\Znp\�_\���\�0\�e\�R^\r0;o�:��A �.��*W\�M��W���r�u��\�\�i�eGK�+��\�o\�\�R\�{G[��\�+f�\�wG��Y�\�\��x�\Z]���\�\�5�x윣���������Y��˩�Y��\�y�\�C�s�\0��}�\�/�\����l\�\�I�&����m��>�CS��e��<�Dx\�T�\�D\�y\��޷\�\�8�)\��7���Nm,\��\�g?�-O��u�\�7\\�W\�@Ļ��L\�G?�\�]K]\Z5)��1%f��/�;�e��\�\�(8�\�\��4�\�\��H}d�\�\�l���[c݇\�\�~2�yk\�B\�R��4[�W��K�\"�\rtv���\�_\rS\�\�WӲ#c\�\�hMQ�b�^�FRK�evyX\�\�P�\�G>�\��x^,enb�<�\�\�Ҽ3fd�R�����^-u�\�\�R�)�#~Wlo,���N�ڔ)SVD1=�\����e\Z\�y��Д/\r�w)�s�\�Ν��F�)�\��w�c���Q�wS\�\�{����\'6ǒ���\�g\�\�6�w^��H��c�9f��	�\�\��5\�\"8\�_�>\0\r\�kz����]\�a�ˍ\���pC�T�\�\��4$m\�j^�\�TK\�\�\��87*��󝊥\�\�q1��\��ү�b�[�vv���J��\�X�7�\�d���\�\��Y�>�OdK�\�\�7��%\�w\�u׮�\�\�\�S,��\�\����&ұ\��\�`\rvY�|\�*�7\�m���ʧq��������@Ӌs�\�:\�-��\�/J`Z�bŝ\�\�x���\�8{{���+��b\�\��\�\��Mxb\�H��\�x\�4Ų�8*\Z�\�>.�P���$\�N�h���x�6\r\�}\�H4?�яv-\�$�\�/L�(\���\��^�3Z���\�\�\�χjZ�9\�ޙ_ B�\�9\"\�y��u~!��1�\�*ň����3ތ:ꨣ�7��\�F�Al����\�_\�C�\�y\0}&O�\�>eʔ\�nذ\���k\��ǤI���,�yB\�`\�@���\�\��wV30\�\��j��uřZ\�\�&\��<TWU\'�q�����\�\�\�\�j\�)f�\"0���~\�\�\�4�\���\�Դ\\�N\Z\�$�Ҁs�\�ɓ_�\�;^܏�Cl����\�cI^::\�Ù�8\��wI�%yꤡN.\�q�4iReʔ)\��l\�\�0�\�G?�9\r]\�E�ä04� 8\rw�\�E\�IC�Vx�|�����\r�g\��\��\�<�nݺg㼔�e˖�\�<�\�/z{{�����\����\��bv\�U�4�f`\�;K�\�IC�\�7i�����B\Z�nKǓ\�\�\Z�!\�]��E\�0f�\�-̀���\�tlni\��U\'\�\'u\0̀�\�\�@t� �?/0���:	�f�\�4��f�,\�S\'\ru\0̀�`��$0��\�:	�f���f��\��	uR�@3\�EX3@c\'\�\�lZ\\V\\�T\'\�I\04^�54\��P\'\�\'u\0̀a\�\0B\�:	\0�\�\0B\�:	\0�\�\0B\�:	\0�\�\0B\�:	\0�\�\0BꤡN�04BꤡN�04BꤡN�04BꤡN�04BꤡN�04M��\�$\0h4M��\�$\0h4M��\�$\0h4M��\�$\0h4M���:	�f@3��P\'\ru�Q�R�x=ck\��\��P\'\ru�Qdٲe\�nذ��(\�֭�%muT\nM���:	�(R�V?{\�=�lz\�\�xA�wN�\�\�\�}*�wT\nM��)p��\0��\��S�,Y�P,y��\�cm\�|�\�I4�c�\�\�7b�?��P\'\�x���R��,\\�P��&mlEs;a\�\�\r�&\�]�$G�\�7�٘J:;;�\��&\�\�\��ᴱ}9�۶���\��&\�ݚ5k����\��֥��T{\0�+4-�ƶo<d� 4��^|�\�?پ}���άY�\�ttt�\�\��\�hhO\�g�򑆨S\��&\�i�����~6mڴ����\�\��\�hh\��|�ɹMM\�\���\�O_�`\�\�\�\'{	\0\�w3�\�,S>�\�Gh�٥�\��m\����Bӎ;^*�+�\�\0㻙��Q`\��\�CM4��k\�ޟa�\�\�k\�\�\�\n�\0�\�Fv�Y&\�6!4AK˽�\�;TfJ�n\�zO\�G\�c\00�\Z\�E�&W\�Ch�\�}��_=o�\�y��.�\��i\0\�-8�hZ�\�r\�/JL�?��5��\�9M\0��\�Mk\�ĉǤ\�hk��@�loo_\�\�y\0��\�M\�\�+�,�!i{}h��\�k�kmm�c\�\0�\�W4�X~�x�\�\rul{Sz\\�\�\�\�x��\0�[p\\A\���\��\�ƍ��#\�\�\�\��Yv\0��\���`���K�R|\�B{\04�ข~b9^\�qq{\04�\�\�3\0��q�g\0@3�\�\n\�\0�f\0\�8�\0\�\0�+p<\0��\�\�\�\�>̞A�\040�qU\�]Z(\��p\�¼\�\�\�e]�\�Uq[,������v\�\�}G�Ti+����ˡN\0�F\�*���Ϙ��衞\�}}\�bO\��\rCU��X:�|������5���tl�mf\�\�=v��\�CZ��vf\Z���WC�\04�\�qU(WK��\n=\�]�\��=\�Rw�a�k\�J�BWO��Ґ�]\�ڿ��\�#�\�\�\�\�/6�}\�v=���c��T�n��\�]0��iJC\�7�\�$\0�\��W\�\�\�}[�\\�\�Ji`YR쩝<����\��~/\�6������/\�r��v�`4���t�ߥc[��a\�ݵËsz�8���WT�3Ş\�?t|���Ŀ��\���͍\��\�P\'\0\�\0#v\\u�*\�\�L\�\���7�ܮRuVw���l���C\�G\n�Z�\�Q�>l\��\�\���\�﫧eK�J��bI��\Z\�$\0�`D��i�eG\�lN��\Z\��\�\�_�bV�\\97�z^��ʵց\�\��M\�R\�,��kg\��\�i�\����N\0�\�qK\�\"�\�\��J��#@\�/\�\�#4�j���\�ܦ=\�O��hM���\� ��K��zq\�T��D\�T\rtv���\�_\rS\�\�\�^u�� \�$\0�\�Wq��+�O\��ĹD�/�K\�\�\�\r\�Rev�����M}W͋s�ji��`ܟ\�`�\�\��N\�R�Ḙ\�@�\�}\�\�Q�sc�)�\�\�W�?]zu\0\�0j��e\�ܛ�@�\048���\0hp\\�\�\0\�\�\�3\0�\0\��g\0@3\0�+\�\0�f\0W\�x\048���\0hp\\�\�\0\�\�\�3\0��q�g\0@3�\�\n\�\0�f\0\�8�\0\�\0�+\��g\0@3\0�+\�--\�ݵ\�\�%\0\�܂\�\n\�s�P��\Z�̫��X�N-�\�\�,����\�I\�;�kn\����\�R�/\n�zO�7@s�+\���\�U��\�\�ޟ�4z*_h��i�eGv�+\�7c�ң����G@s�+\����L��\�b�vR�T]\���X�׹`\�\�{��i��0��\�R\�⮹\�٣\0��\�\�\�xvD6\�T�.��\n\�\�\��\�\��H���\�g�T?��Jq~\�|V*=	\0�[p\\1��\�b��ϧ�\�\�7�\��\�b�rnWO�\�\�=\�J�o�N\�.e\�)W�v\�s+�\�I\0\�܂\�q}<\�9L\�r�rmf,�\�*\�\�.\�Y�\�4 \�\����Ruzܞ7��mq��yWؓ\0��\�\��x.�*m1�\�\�f\�\�=6\�\�K�\�_\�\nK�j�d\�;�*�U;/Q;;f�\�I\0\�܂\�q{<\�_��kn\��J\�Y\�R\�\�4]3M�dog�zu���ko��\n�\�\�tܑ�\�\�\�({\04�ย)�\�\�CW���\�\�[_7\�\�p�q\0\�\�\�\�\�Т8��\�S��\0�f�f=�NH\�˃��\�\�\�S\�)\�I\0@3@3Gw�\�0a\�\��\�$\0�����\nMmmm�\�C��\0�f\0\�R\�s�2˄:	\0h���M\�eB�\04P\'\rI\�2�N\0�84}�\�\\��\�\�I\0@3\0{SٹMf�P\'\0\�\04>�Np\\�N\0��$I_�z�m<���J��,Y�\�8ģ��7Y�l\��c�#u\0\��2��/_�lذ!y饗�\Z\�?�|�t\�\�\�\0�yG%\�$\0h4�H\�0	L�&8��dɒG���\0�\��\"�$O`=#\rM/;*Q\'@3�E\�\ZaeT�&��N�f@30CӋ��MV?t}�ޯe#����	u\0\�4}hڼq]��%?�\�\�F\��	;B\�$\0�h\�д\�g��G`\�\�ڟ-v�&\�I\0@3\�ܡ\�\�\�\�M�#4�N\0���M+�t�\�1aGhB�\04B�\�$4�N\0����\�\r�\�1aGhB�\04M�V\�;o�\��	;B\�$\0�h\�\��\�u+��?��\�Ҽ��xL\��P\'\0\�@S��O>|\��)\�t�&\�I\0@3 4mۖ��\�{.\�K\�Ǆ�	u\0\�4mhڼq]�\�{\��\��\�6�\�\�>\�\�EQ�\�S\0 41\�BӶmɳ��\�-�d����\�&�5\�$4�O5�t�<Xhjmm=Ş\0��Q���]2\�$4q�\�\�݃���\'L�p��\0B�(4\�\�\�\�`�N\�İ\�\�	�����S\�!\0�e�i_S>���}���\�mz\�,\0M�\�\�dM�Hm\�\�\�&\�2�Є\�dM\�IC\�B�L\0�\�$4B��ם\�t�=\0BB�!4�g�\�\�m2\�\0BB�!4�joo��\�\�\��\�\�x[:V�\�}�M[\��dߒ�c;{\0�&�&�Ihj\Zq)�t,I\�K�}�m�QK��l\��Є\�$41�k\�\�\�xA�\�X>q\�\�c\�M\0���&ƕ\�\�\�\�\'M����\0\�\�ޞ��\��}R�V���~:�����$����M֭[�\�{\�ɜ9s�/|\���\�f�N\0 4!4	M���tJ\Zr^�K7\�xc�aÆdolܸ1\�>�W�ҟy��\0BB�\�\�XL�\��3fd�J�\"�w\�E�Nf�\0@hBh��5\�\��K�fΜ�k	޾��{�f\�\�T\�8{\0�&�&��1\'\rL��3L��\�S��\��6\0MMBcJ\\V���}]�7\�R��s�,\�\0��}0aÅ�����{2\�BӒ<\�\�E��o�\�l\0M싴��õk\�\��+�\�W�r�@Ͷ\�46B\�\��=9ta(6\�6\�\�\��_�!f�~�\�_�\�W\�\�w9�w��\�\��~�\�˶n\��r\�Tm۶\�w�\�r˪�\�;\��\nMc/4\r\�\�ɡ�c}\�ᖴ��~�\�6\��\�\�\�}\�\�H����\�t��\0M\�\�_��\�{\�W5VO>�\�\�ٳg_\�\�\�q�\�4�CӾ�=9���n\�\�\�)�\���{\�砆������,��q�f\�_ztW�\�\�U�]�\�W,U/\�\����~Nww�a\�\�5K�Z��[G�Y�\��۷o\�1Ts�cǎ\�/�\�b��i�it����{2b�i\�\�S�\�\�W\��\�@��nݺ�\�\Z!\0\'����\�\�{jgF�)��\�M+��\�թ�rmf��3zz�2V1\�\�NI?�X��[\�|��T}�ك\�s\�=��M�6��\�Al�\�7�YY�h��2�ƃ>�\�\�\�3m\�߮ʌ\\h��yJǖ��u���.?^�����8j6ξ`vr\�%�\��b\�~\�\�\�Y]�\'\�\\t\�n\�G\����\�\�]\�Ƕ;o�&�����h^\��(�Jى\��r�x<7~F�K=a\�ҥ�R݈�Á�{��\�\�6i\\�z(\��>�4\00N\�\�R�T=�\�S���y�o��\\;.f�\n�zO�\�.�Y*U����zu�x�Z+̩��\�S\�,�j\'5�~�+tuvv�\�}�\��\�;^ٛ\�jӦM7M�2�\�\�ں05b!!�\�\'\�����+�J\�m\��\�\�ꫯ\�\�޺uk��׼\�5�n�\�3\�d\�\����\�{\�\'Fl�\�q\�\���=\�\�\�\��븽`^\�]1\�3\����ۥ��kn\����\�R�/�׋�x�\�x(�;�W7L�\�l�	\0\�/\�{�(�T�P(W/˖\��T����\��7J��\��\�1���\�m\���r\�_\�&{r��\�\�/�|\�ƍ7r�\�\�|;ε�\�\�y#yNS��#�<2��\��}�6�n�\�#�$\�?�|\�F �����\�{l��\�g�}\�s�Kn���\�\�nKn�\�\�MozS�ַ�5{~����o}k�\�\����\� ��\�9\�\�\�x#$��=\rAQ\Z�Ѳ\�Ȯreb��\�ȑ�����m�\�\0\�G�4���1S\�U�~&.�MO\Z�\n\�R\�\��\rPW�:+R\�yP�\�I�ns�&{rw\'N|c{{{O\Z6l߾}K]O���\��&}le\�\�\�\�\�\�xK���M\\b樥o\�}\�{_r��\�\Z�V�\\�<�\�S\�\�1\"\�\�\�4\��\�O\��C�u�\�=h(�۵k\�f\�ӟ�t��H^=o_��cYߛ\'3\���B����\�q�b\�E��#\rV����\�[�\����#�6�\�,W\�\0�/4�+cy^\\\�!m���\�\"\�LR\�,=V�Ns4G��w�˵Sg�T?Y(W��D�4V�M\�	\�bq\�\�ի\�mٲe\�%�\\�h\�\���ێ\�LS�|�\\.g��\�Gw���\"\�x㍻�\�\�)n#D�_��\�R�h \��k��&��:\�3�٦���Z��̞=;{NOOOv�/׋\�\����\�\Z�\�i\Z\�\�s,�7Cv�]]���^�\�L�^���\�_Q3d�ߗ��lֹoV*\�\��?�\�\�R>�	\080\�){�x\�ˋ�Kr\��E3\�\�\�V\�ra�\�\�P��8B\"�\�ҷCyPhF\�\�\��\"@Ej\�=�ۿ�\�\�\�9��/�=��듩S�\�M�ԧ>�۶�7o\���/_�\�~\�;\�\��}_|q\�FÇ\�\�\�9\�K�>����i�\��\�U1\�\�\�l\�n��\��\�\�(\�sʕ�\��\�\�\�\��\�\����\�o�~�\�~q�ɓ\'\�M\�V\0��{|�f�\�\�,�\�EfΜ�}}\�w6\\:�\���1\�BEp\�/\�\��9�LQ�8\�)n#�\�o�\�/$W]uU�ؚ5k�\�U�V\�\nQ#�\����k\��l9^�63�\�u�kg\�,{g\Z�\�\��&Kuz\�ƅcv^ �v\�h�\�\�m\�\�L�p0\�|�\���L\�U=\0�l$BS\\�!�-�s�n�ᆆ�:o~�`\�?\�\�K�\���BS�\�X�7cƌ䵯}m\����\��\�\�\�\���\�\��n�L/��ߡ\�GC��q���\�g�U;g\�\�=6\�\�>��\\]W\�\��\�\�N\�\�wڹL�\����3Rc 4����m:\����7iҤu�;YU\0á)&J�p�_�<_��vS~nQ,��ۿ���ɂN�{#E���\�K�\'�|�\��R1�t\�\�&��\�=�*?�*�0E�����u\�\�\�$�\�Ǆ�����/q^c\\�%.���\�c�)�\��\�n\��\�cYo�T]��;\�\����9ڤ\�Q-5\�\�\�\�ci\�\\�J�,\�x��\0h�\�\�6�P(d�dҤI{\\\"�\�\"0E���@\���\�g\�-\�E!\Z����>:�\�\'>1\�e\�c4�\�\�C\�hZ�7\�Ņ⪚q�\�ᄮQ�ޝ�\��p\�M�78E`�5kV�yL�\�\�ڎS\�\0\0\�xh\Z(H\�\�\�`\��?io\�QG�kVk�K�\"��\�>�ƿ\�\�\�\��B\�3N��T/�\�o�ɲ<\0�����Y�ᦣ>8\�9Nqq�\�{{���\�C�9L1\�@�%{\0@h�b\�)\�CO�����\0ܘEʗ\�\��u\�\�e�\�/�Jəg����y�$/���\n\0 4	M�+q�S��\��q,w\0�\�$41\�\�\�\��\�\�\�\�	K1�\�*y\0\0B�\�D\�hoo��4<\�0t[:�L\�־��kұ(\���\0 4	MB\0\0M�\�\0\0B�!4\0�\�dM\0\0 4B\0\0M�\�\0\0MB\0\0 4	M\0\0�\�$4\0\0B�\�\0\0M�\�\0\0B�!4\0�\�dM\0\0 4B\0\0M�\�\0\0MB\0\0 4	M\0\0�\�$4\0\0B�\�\0\0MB��\0\0�&Ch\0���R�+�glMC\�ˎJ\0\0E�-[�\�\r�Q0֭[wK\Z�uT\0�(R�V?{\�=�lz\�\�.#7\�������t|\�Q	\0\0�Lڨ��dɒ�biX�Sc���Q�	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0h\Z�\��sB�.\0\0\0\0IEND�B`�',1),('7502',1,'/home/frank/Workspace/cebrains/Server/hrc/hrc-admin/target/classes/processes/ExpenseProcess.bpmn20.xml','7501','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n             xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\"\n             xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\"\n             typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\"\n             targetNamespace=\"http://www.flowable.org/processdef\">\n  <process id=\"Expense\" name=\"ExpenseProcess\" isExecutable=\"true\">\n    <documentation>报销流程</documentation>\n    <startEvent id=\"start\" name=\"开始\n\"></startEvent>\n    <userTask id=\"fillTask\" name=\"出差报销\" flowable:assignee=\"${taskUser}\">\n      <extensionElements>\n        <modeler:initiator-can-complete xmlns:modeler=\"http://flowable.org/modeler\"><![CDATA[false]]></modeler:initiator-can-complete>\n      </extensionElements>\n    </userTask>\n    <exclusiveGateway id=\"judgeTask\"></exclusiveGateway>\n    <userTask id=\"directorTak\" name=\"经理审批\">\n      <extensionElements>\n        <flowable:taskListener event=\"create\" class=\"com.cebrains.hrc.modular.flowable.handler.ManagerTaskHandler\"></flowable:taskListener>\n      </extensionElements>\n    </userTask>\n    <userTask id=\"bossTask\" name=\"老板审批\">\n      <extensionElements>\n        <flowable:taskListener event=\"create\" class=\"com.cebrains.hrc.modular.flowable.handler.BossTaskHandler\"></flowable:taskListener>\n      </extensionElements>\n    </userTask>\n    <endEvent id=\"end\" name=\"结束\"></endEvent>\n    <sequenceFlow id=\"directorNotPassFlow\" name=\"驳回\" sourceRef=\"directorTak\" targetRef=\"fillTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'驳回\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"bossNotPassFlow\" name=\"驳回\" sourceRef=\"bossTask\" targetRef=\"fillTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'驳回\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow1\" sourceRef=\"start\" targetRef=\"fillTask\"></sequenceFlow>\n    <sequenceFlow id=\"flow2\" sourceRef=\"fillTask\" targetRef=\"judgeTask\"></sequenceFlow>\n    <sequenceFlow id=\"judgeMore\" name=\"大于500元\" sourceRef=\"judgeTask\" targetRef=\"bossTask\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money > 500}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"bossPassFlow\" name=\"通过\" sourceRef=\"bossTask\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'通过\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"directorPassFlow\" name=\"通过\" sourceRef=\"directorTak\" targetRef=\"end\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${outcome==\'通过\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"judgeLess\" name=\"小于500元\" sourceRef=\"judgeTask\" targetRef=\"directorTak\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money <= 500}]]></conditionExpression>\n    </sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_Expense\">\n    <bpmndi:BPMNPlane bpmnElement=\"Expense\" id=\"BPMNPlane_Expense\">\n      <bpmndi:BPMNShape bpmnElement=\"start\" id=\"BPMNShape_start\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"285.0\" y=\"135.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"fillTask\" id=\"BPMNShape_fillTask\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"405.0\" y=\"110.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"judgeTask\" id=\"BPMNShape_judgeTask\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"585.0\" y=\"130.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"directorTak\" id=\"BPMNShape_directorTak\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"735.0\" y=\"110.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"bossTask\" id=\"BPMNShape_bossTask\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"555.0\" y=\"255.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"end\" id=\"BPMNShape_end\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"771.0\" y=\"281.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">\n        <omgdi:waypoint x=\"315.0\" y=\"150.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"405.0\" y=\"150.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">\n        <omgdi:waypoint x=\"505.0\" y=\"150.16611295681062\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"585.4333333333333\" y=\"150.43333333333334\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"judgeLess\" id=\"BPMNEdge_judgeLess\">\n        <omgdi:waypoint x=\"624.5530726256983\" y=\"150.44692737430168\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"735.0\" y=\"150.1392757660167\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"directorNotPassFlow\" id=\"BPMNEdge_directorNotPassFlow\">\n        <omgdi:waypoint x=\"785.0\" y=\"110.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"37.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"37.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"110.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"bossPassFlow\" id=\"BPMNEdge_bossPassFlow\">\n        <omgdi:waypoint x=\"655.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"771.0\" y=\"295.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"judgeMore\" id=\"BPMNEdge_judgeMore\">\n        <omgdi:waypoint x=\"605.4340277777778\" y=\"169.56597222222223\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"605.1384083044983\" y=\"255.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"directorPassFlow\" id=\"BPMNEdge_directorPassFlow\">\n        <omgdi:waypoint x=\"785.0\" y=\"190.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"785.0\" y=\"281.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"bossNotPassFlow\" id=\"BPMNEdge_bossNotPassFlow\">\n        <omgdi:waypoint x=\"555.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"295.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"455.0\" y=\"190.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('7503',1,'/home/frank/Workspace/cebrains/Server/hrc/hrc-admin/target/classes/processes/ExpenseProcess.Expense.png','7501','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0M\0\0Y\0\0\0D\�Ĉ\0\0.�IDATx\�\�\���u}?��!*�Zu��e\��~�?�:G[�:آ�R4s\\.\��]0�\�n�AA\�!\�4\n%�ݤQ��P\ZhJ�\�v�$\�OL��� $��	\r&AH�\��yrO\�\\�\�r�w����\�\�\�>{�{�\�\�\�\��lK\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\\N?��\�0\�|x�\0�&M��Ռ�K�\�*\�Z�J������\�\�]s{\�1\�s���\�\�n/�N\0�ƥ\�\�[_W쩝��P�^7��\�P,W�ʵ����޿̂U�y�S\�\�O-�+\�{*\�\�*U��P\'\0\�\0\�V\�0��\�\�i�\����U\�v�M]\�\�\�L���mv�\�\�\�\�j\�^D�\04�K1�T(UO+�\�.=o^\�\�.(׎�Y�¼\�\�\�f�J��\�\�^�=^�\�\nsjU,\�N�\'Q\'\0\�\0\�\���\�\n=�/\�\�˲ey=\�/\�达v\�n��Ti+ί\�*�:\�6\�\�*\��%\�k�\'Q\'\0\�\0\�N\�\�\�\�LQW�����C��4��ڭ��PW�:+R\�yP�\�I�4/B�=�:	\0h���\\�\��\�\"i�\"BԴҲ#c&�P�>3P��\�\�F���byތ�\�\'\�\�Ү�\�ٓ��\0�f���J���\��\��ť\�\�=\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0\�$\0�\0@�\0̀f\0@�\04\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�\04\0��\0�f\0@�\04\0\�$\0�\0P\'\0\�\0�:	\0h\0\�I\0@3\0�N\0�\0u\0\�\0��\0�f\0@�T\'@3\0�:	\0h\0\�I\0@3\0�N\0��C\�\��\\�TJ\�Z�n{\0�&\0\�$Ir\�o~󛍩����\�\�\0�\0��f͚\�\'}\�\�ummm�\�+\0 4�z�\��d��\�;�:�f\�Z\�\�\��{\0�&���~��\�I?�6mZ\�\�\�\�c\�\0�\�\�Ԟ~�\�˒,\\�p}Z7O��\0`��E��\�S@�Js\�n۶\�w��;v�T,WL�0\�p{\0\�gh:!/�Z[[O���f�v\�\���!�^�zM[[[�\����\�$4\�\�\�S��\�{\�Ce�d\�֭�D\�\�\�\�8\����N(4��.\�\��կ�7\��\�L�\\r\�\�4��N�\�mz\�,@K\�-�\��\�\�?~�Yy\0h�дǹM\�e\�i\�ĉǤ\�hk��@�loo_\�\�y\0\�$Ґ�\�,@cW^ye9\rI\�\�Cӵ\�^{]Z;\�\�;\0\�<�\�\�u\�2�l�\0�*\�HZ�x���)���:::\�b\�\0@\�\�m2\����\�?�c7nܖ�����\�\�r\0�9CSv%={��\�]*�\�υ�\0S�$��^���x`{�RI�,Y2&G����o\�\�\�M�-[�\�\�\�u�`��\�+���\�~�:	\0\����\�˓\r6$/���1B\��\�O�.]�B\�|\�Q	\�\��\�֭S\'`_\�;�Өi^Z�dɣ�JP\'\ru�Q$��x!=#m^vT�:i��\0�\"�V܋�j\\\�\�IC�`,6/nz6Y�\��\�\�z����:\���\0uR�T\'h�f`�\�u\�\�^�����vq_<\�E\\3\0\�:�N\�\�\�����\��@>\��l�q\�\0��\�:	@s7�־>`3�y\��:�N��\04u3�rI��\�@<\�E\\3\0\�:�N�\�h@�T\'\�I\04�G\\j�f �\"�\0uR�T\'h\�f`ս�l\�1/\�P\'\�Iu��n~�nE��G3�\\r�\��y\��:�N��\04u3\�ɇoأ����k@�T\'\�I\04۶%�\��\�KN\��\�1/\�P\'\�Iu��m\�\�yoy����O�\��:�N��\04_3�m[�\�j�_�/�\�Gl\�z7U3\0\�:�N\�\��P\�z7U3\0\�:�N\�\�\��޼k:ػ�^\�5\�\�[\�\�Iu\0\��/H�\�\�Ë�f`�89I\�-\�:�N�04\�9)\�1�\���D�4\�I\04�f�\�S�b�\�y�I�4\�I\04�f�=\�P��N\Z\�$\0�C3\�4N\"\�;\�I�4\�I�\�K�\�+VL�\����t�\�\�/L�>�厎�\��\�OO&O��\�_�\�K�^z\�\��\�7�\��\�c�C30J\�P�Ȍ�:i��\0\�K�\�~�\rW\\q\�˅B!Y�`A��$\�֭K�nݚ��ݰaC�\��8ICU\�\�յ�X,�����{P3`hF��Z�7�4\�\�Q\'\ruhVi8:\�\��\�\�]t\�EY z\�W���\�#�$_�җ^�2eʣ\'N<\�\�\���L�\�\��6NꤡN4�f�\�\��\�\�瞛,Z�hXa��ŋ\'g�u\��N�C30�Ӂz>ꤡN\�50\�s\�=N�6-�\�/���կ�4�m9\�sγ�5�\��!2\�E�\�A�4\�I`���Lq�ҁ?���ss[[\�d{Y3�\����Ό�:i��\0;\�9L�$\�@\�05�q:\�36��\�8{[3�\� \�����3�n����\\B�4\�I�\�\�U�����\�0Lw\�uז����\�q̀f���N�>\�E�\�\�\�\�4\��\'u\�P\'�f�p\�\���\��\�w�K��S���LO3�\� �pf_�I��\�S>Ψ{\�9NꤡN\�(f�\�s�\�\�\�O�ӗ\�6i4@�Fh�\�\�h�kl\�\'u\�P\'�f�bŊ)q�����p�u\�Y\�O?��w\������mI:�L3�h�\�y��t�0�P�ഷ��p�TO�4\�I�|\�{\�[z\�\�&�Ҝ9sICӅ���K�_#��\�\�\�k4\�p<\�cp*8\r70\���\�[\�8���:	��g\�~\�8��\��\��U�B�\�\�\��f�n\�\�\�Ùf`<\�\r\�\����\�a��r\�qR\'\ruhӧO�\�g�=��i���\��5��mv��Z\�h4\�\�x\�\��4Xp�\�~�����N\Z\�$0Vttt$[�n=��)~_�µ\�\�?�\�@�;��E3�O\�s\��\�\�4\nN���\'\�8���:	�\����/f\�c��\�O~�<���\�pL\��8\�\��p3g?\�\�a\rf�~\�w���\�\�N\ZB0N�y\�;�LӖ-[֛i:h\�>\�\�\�zʄ	�\�FC3p\�e�e�\�ʕ+�\��裏N&M��\�F\�s\�i�m_��\�\'\�w\�^�;��\�$\�|\�;w}?��3\�HV�X��>\��\�?�[��Z\��\�4\r\�p\�4���k\�ʵ��mS�\�{b�\\���T�*n��\�??\������\�(�*m\�}yT\�\�o�1����\�\�߼ys�t\�\�AC_^K_�\����y\�l��\�\�}O<�\���6m\�K�����8�\�9M�\�\�\�\\���\��\�Of/\�?��ϓ�7f/�m�\�\��6��\�{��&\�|\\�~�m\��\�\�c�X�\"�\�\�~7��@\�\�={v\�\�\�p/��®\�\�q\�T5��x�(�[\�9M������\�\�\�\�]\�ڿ�\���#�=�\�7U�\�c\�H�Q��rT\��\�\\�\���;ұ%��1��\��\�iŞڙip:~�\�\�8\��\�/g�妛n\�U\����~��\�\�n\��\�\�v֬Y\�\�~��\��s��\�n\�y�ᇓ�\�;���\�oϞ\�}ӛޔ���o\�jm�Ϸ���ݾ\�{\�#4���\\u����\���\�]=\�5������:@s9b\�@�E�e_\�;�yh\�\�\�l��X��\���������\�SO=5\�\�Q�s\�6�ϛ��\�\�}�}\�k�p3^�݉\'����hH4c\�xnT�vfg�ˊ7�z\�p�SS_=\�r\�44�=p�\�>,�)�*���\�?�!\�#d��\�#�\�\�\�\�/6zn�]O%�h�l��T�n��w�wͫ�+f��\�\�s��3\��u2\��Po�\�Eq߃>������E�]<���\r�<4U*�=PԸ��\�ah�7�\�Gy$�]�vm�>�\�Og_M@ӻ�\�+�=ԟ\�4s\�̻|N\�~7���?⡩�8f����\�с�3FӧO϶�\�?�ӆ\�\�x�PH\���7$�^ziv\�7��8�v5�ۯZ�*����\'y�ߜ\�t\�s���\�䪫�\��4��x 8\rX�����\0\��Nhi\�\�i\�*W?��r\�\�bO\�\�\�)�����ko��ϛ\���Hi��B,\�\�o��9s�J��]:�ExJƂ��*\�\��\��\�\'\�_���\�\�_\��=\�iX�v��\�\�c��N\�5)�ֵ�-]�o\�\�\�F�\�y�7�\���\�\�ncyq\�F�����_\�\\sM����=[��M�v�V\�~g^\'\�6�+�\��f�\�I�)|\�+_�\�\���W^y\�]<oҤIO�/f\����\nMq�x�=�\�s��_\�r�Xv2\��x��\�!\�ϻ\���\�\�/Ξw\�9\�4\�>O�N?��\�p\�\r\�;��ꗵ\�\�\�o\'O���X�fͮY�h4c\�P3={���\�\rN��S�m�G�\�S��ΠR����\�\�e�T9?�\�5Y�\�|��\��>R(\�\�w�\����\���z\�@�\��\�N˖\�*WŒ�\�Z\'# \��Lq�\�yCQ�b�\\<�\��\�R\�|��U�\�z|u.~�F�\�1s\�ҷ\\9\���:u\�7�>��O\�qnT��/_�\�\�yQ�\�9�\�$0\�͘1\����Ǉjiލi`Zn��\�\�/��\�[�</\�]��\�h9\\��=�]�����\�\�\�\�b�(\�u\�Ot��.q[.�wm�;���\\�Xj��c_\�K��/m\��)\�8\�m`\Znp\�_\���\�0\�e\�R^\r0;o�:��A �.��*W\�M��W���r�u��\�\�i�eGK�+��\�o\�\�R\�{G[��\�+f�\�wG��Y�\�\��x�\Z]���\�\�5�x윣���������Y��˩�Y��\�y�\�C�s�\0��}�\�/�\����l\�\�I�&����m��>�CS��e��<�Dx\�T�\�D\�y\��޷\�\�8�)\��7���Nm,\��\�g?�-O��u�\�7\\�W\�@Ļ��L\�G?�\�]K]\Z5)��1%f��/�;�e��\�\�(8�\�\��4�\�\��H}d�\�\�l���[c݇\�\�~2�yk\�B\�R��4[�W��K�\"�\rtv���\�_\rS\�\�WӲ#c\�\�hMQ�b�^�FRK�evyX\�\�P�\�G>�\��x^,enb�<�\�\�Ҽ3fd�R�����^-u�\�\�R�)�#~Wlo,���N�ڔ)SVD1=�\����e\Z\�y��Д/\r�w)�s�\�Ν��F�)�\��w�c���Q�wS\�\�{����\'6ǒ���\�g\�\�6�w^��H��c�9f��	�\�\��5\�\"8\�_�>\0\r\�kz����]\�a�ˍ\���pC�T�\�\��4$m\�j^�\�TK\�\�\��87*��󝊥\�\�q1��\��ү�b�[�vv���J��\�X�7�\�d���\�\��Y�>�OdK�\�\�7��%\�w\�u׮�\�\�\�S,��\�\����&ұ\��\�`\rvY�|\�*�7\�m���ʧq��������@Ӌs�\�:\�-��\�/J`Z�bŝ\�\�x���\�8{{���+��b\�\��\�\��Mxb\�H��\�x\�4Ų�8*\Z�\�>.�P���$\�N�h���x�6\r\�}\�H4?�яv-\�$�\�/L�(\���\��^�3Z���\�\�\�χjZ�9\�ޙ_ B�\�9\"\�y��u~!��1�\�*ň����3ތ:ꨣ�7��\�F�Al����\�_\�C�\�y\0}&O�\�>eʔ\�nذ\���k\��ǤI���,�yB\�`\�@���\�\��wV30\�\��j��uřZ\�\�&\��<TWU\'�q�����\�\�\�\�j\�)f�\"0���~\�\�\�4�\���\�Դ\\�N\Z\�$�Ҁs�\�ɓ_�\�;^܏�Cl����\�cI^::\�Ù�8\��wI�%yꤡN.\�q�4iReʔ)\��l\�\�0�\�G?�9\r]\�E�ä04� 8\rw�\�E\�IC�Vx�|�����\r�g\��\��\�<�nݺg㼔�e˖�\�<�\�/z{{�����\����\��bv\�U�4�f`\�;K�\�IC�\�7i�����B\Z�nKǓ\�\�\Z�!\�]��E\�0f�\�-̀���\�tlni\��U\'\�\'u\0̀�\�\�@t� �?/0���:	�f�\�4��f�,\�S\'\ru\0̀�`��$0��\�:	�f���f��\��	uR�@3\�EX3@c\'\�\�lZ\\V\\�T\'\�I\04^�54\��P\'\�\'u\0̀a\�\0B\�:	\0�\�\0B\�:	\0�\�\0B\�:	\0�\�\0B\�:	\0�\�\0BꤡN�04BꤡN�04BꤡN�04BꤡN�04BꤡN�04M��\�$\0h4M��\�$\0h4M��\�$\0h4M��\�$\0h4M���:	�f@3��P\'\ru�Q�R�x=ck\��\��P\'\ru�Qdٲe\�nذ��(\�֭�%muT\nM���:	�(R�V?{\�=�lz\�\�xA�wN�\�\�\�}*�wT\nM��)p��\0��\��S�,Y�P,y��\�cm\�|�\�I4�c�\�\�7b�?��P\'\�x���R��,\\�P��&mlEs;a\�\�\r�&\�]�$G�\�7�٘J:;;�\��&\�\�\��ᴱ}9�۶���\��&\�ݚ5k����\��֥��T{\0�+4-�ƶo<d� 4��^|�\�?پ}���άY�\�ttt�\�\��\�hhO\�g�򑆨S\��&\�i�����~6mڴ����\�\��\�hh\��|�ɹMM\�\���\�O_�`\�\�\�\'{	\0\�w3�\�,S>�\�Gh�٥�\��m\����Bӎ;^*�+�\�\0㻙��Q`\��\�CM4��k\�ޟa�\�\�k\�\�\�\n�\0�\�Fv�Y&\�6!4AK˽�\�;TfJ�n\�zO\�G\�c\00�\Z\�E�&W\�Ch�\�}��_=o�\�y��.�\��i\0\�-8�hZ�\�r\�/JL�?��5��\�9M\0��\�Mk\�ĉǤ\�hk��@�loo_\�\�y\0��\�M\�\�+�,�!i{}h��\�k�kmm�c\�\0�\�W4�X~�x�\�\rul{Sz\\�\�\�\�x��\0�[p\\A\���\��\�ƍ��#\�\�\�\��Yv\0��\���`���K�R|\�B{\04�ข~b9^\�qq{\04�\�\�3\0��q�g\0@3�\�\n\�\0�f\0\�8�\0\�\0�+p<\0��\�\�\�\�>̞A�\040�qU\�]Z(\��p\�¼\�\�\�e]�\�Uq[,������v\�\�}G�Ti+����ˡN\0�F\�*���Ϙ��衞\�}}\�bO\��\rCU��X:�|������5���tl�mf\�\�=v��\�CZ��vf\Z���WC�\04�\�qU(WK��\n=\�]�\��=\�Rw�a�k\�J�BWO��Ґ�]\�ڿ��\�#�\�\�\�\�/6�}\�v=���c��T�n��\�]0��iJC\�7�\�$\0�\��W\�\�\�}[�\\�\�Ji`YR쩝<����\��~/\�6������/\�r��v�`4���t�ߥc[��a\�ݵËsz�8���WT�3Ş\�?t|���Ŀ��\���͍\��\�P\'\0\�\0#v\\u�*\�\�L\�\���7�ܮRuVw���l���C\�G\n�Z�\�Q�>l\��\�\���\�﫧eK�J��bI��\Z\�$\0�`D��i�eG\�lN��\Z\��\�\�_�bV�\\97�z^��ʵց\�\��M\�R\�,��kg\��\�i�\����N\0�\�qK\�\"�\�\��J��#@\�/\�\�#4�j���\�ܦ=\�O��hM���\� ��K��zq\�T��D\�T\rtv���\�_\rS\�\�\�^u�� \�$\0�\�Wq��+�O\��ĹD�/�K\�\�\�\r\�Rev�����M}W͋s�ji��`ܟ\�`�\�\��N\�R�Ḙ\�@�\�}\�\�Q�sc�)�\�\�W�?]zu\0\�0j��e\�ܛ�@�\048���\0hp\\�\�\0\�\�\�3\0�\0\��g\0@3\0�+\�\0�f\0W\�x\048���\0hp\\�\�\0\�\�\�3\0��q�g\0@3�\�\n\�\0�f\0\�8�\0\�\0�+\��g\0@3\0�+\�--\�ݵ\�\�%\0\�܂\�\n\�s�P��\Z�̫��X�N-�\�\�,����\�I\�;�kn\����\�R�/\n�zO�7@s�+\���\�U��\�\�ޟ�4z*_h��i�eGv�+\�7c�ң����G@s�+\����L��\�b�vR�T]\���X�׹`\�\�{��i��0��\�R\�⮹\�٣\0��\�\�\�xvD6\�T�.��\n\�\�\��\�\��H���\�g�T?��Jq~\�|V*=	\0�[p\\1��\�b��ϧ�\�\�7�\��\�b�rnWO�\�\�=\�J�o�N\�.e\�)W�v\�s+�\�I\0\�܂\�q}<\�9L\�r�rmf,�\�*\�\�.\�Y�\�4 \�\����Ruzܞ7��mq��yWؓ\0��\�\��x.�*m1�\�\�f\�\�=6\�\�K�\�_\�\nK�j�d\�;�*�U;/Q;;f�\�I\0\�܂\�q{<\�_��kn\��J\�Y\�R\�\�4]3M�dog�zu���ko��\n�\�\�tܑ�\�\�\�({\04�ย)�\�\�CW���\�\�[_7\�\�p�q\0\�\�\�\�\�Т8��\�S��\0�f�f=�NH\�˃��\�\�\�S\�)\�I\0@3@3Gw�\�0a\�\��\�$\0�����\nMmmm�\�C��\0�f\0\�R\�s�2˄:	\0h���M\�eB�\04P\'\rI\�2�N\0�84}�\�\\��\�\�I\0@3\0{SٹMf�P\'\0\�\04>�Np\\�N\0��$I_�z�m<���J��,Y�\�8ģ��7Y�l\��c�#u\0\��2��/_�lذ!y饗�\Z\�?�|�t\�\�\�\0�yG%\�$\0h4�H\�0	L�&8��dɒG���\0�\��\"�$O`=#\rM/;*Q\'@3�E\�\ZaeT�&��N�f@30CӋ��MV?t}�ޯe#����	u\0\�4}hڼq]��%?�\�\�F\��	;B\�$\0�h\�д\�g��G`\�\�ڟ-v�&\�I\0@3\�ܡ\�\�\�\�M�#4�N\0���M+�t�\�1aGhB�\04B�\�$4�N\0����\�\r�\�1aGhB�\04M�V\�;o�\��	;B\�$\0�h\�\��\�u+��?��\�Ҽ��xL\��P\'\0\�@S��O>|\��)\�t�&\�I\0@3 4mۖ��\�{.\�K\�Ǆ�	u\0\�4mhڼq]�\�{\��\��\�6�\�\�>\�\�EQ�\�S\0 41\�BӶmɳ��\�-�d����\�&�5\�$4�O5�t�<Xhjmm=Ş\0��Q���]2\�$4q�\�\�݃���\'L�p��\0B�(4\�\�\�\�`�N\�İ\�\�	�����S\�!\0�e�i_S>���}���\�mz\�,\0M�\�\�dM�Hm\�\�\�&\�2�Є\�dM\�IC\�B�L\0�\�$4B��ם\�t�=\0BB�!4�g�\�\�m2\�\0BB�!4�joo��\�\�\��\�\�x[:V�\�}�M[\��dߒ�c;{\0�&�&�Ihj\Zq)�t,I\�K�}�m�QK��l\��Є\�$41�k\�\�\�xA�\�X>q\�\�c\�M\0���&ƕ\�\�\�\�\'M����\0\�\�ޞ��\��}R�V���~:�����$����M֭[�\�{\�ɜ9s�/|\���\�f�N\0 4!4	M���tJ\Zr^�K7\�xc�aÆdolܸ1\�>�W�ҟy��\0BB�\�\�XL�\��3fd�J�\"�w\�E�Nf�\0@hBh��5\�\��K�fΜ�k	޾��{�f\�\�T\�8{\0�&�&��1\'\rL��3L��\�S��\��6\0MMBcJ\\V���}]�7\�R��s�,\�\0��}0aÅ�����{2\�BӒ<\�\�E��o�\�l\0M싴��õk\�\��+�\�W�r�@Ͷ\�46B\�\��=9ta(6\�6\�\�\��_�!f�~�\�_�\�W\�\�w9�w��\�\��~�\�˶n\��r\�Tm۶\�w�\�r˪�\�;\��\nMc/4\r\�\�ɡ�c}\�ᖴ��~�\�6\��\�\�\�}\�\�H����\�t��\0M\�\�_��\�{\�W5VO>�\�\�ٳg_\�\�\�q�\�4�CӾ�=9���n\�\�\�)�\���{\�砆������,��q�f\�_ztW�\�\�U�]�\�W,U/\�\����~Nww�a\�\�5K�Z��[G�Y�\��۷o\�1Ts�cǎ\�/�\�b��i�it����{2b�i\�\�S�\�\�W\��\�@��nݺ�\�\Z!\0\'����\�\�{jgF�)��\�M+��\�թ�rmf��3zz�2V1\�\�NI?�X��[\�|��T}�ك\�s\�=��M�6��\�Al�\�7�YY�h��2�ƃ>�\�\�\�3m\�߮ʌ\\h��yJǖ��u���.?^�����8j6ξ`vr\�%�\��b\�~\�\�\�Y]�\'\�\\t\�n\�G\����\�\�]\�Ƕ;o�&�����h^\��(�Jى\��r�x<7~F�K=a\�ҥ�R݈�Á�{��\�\�6i\\�z(\��>�4\00N\�\�R�T=�\�S���y�o��\\;.f�\n�zO�\�.�Y*U����zu�x�Z+̩��\�S\�,�j\'5�~�+tuvv�\�}�\��\�;^ٛ\�jӦM7M�2�\�\�ں05b!!�\�\'\�����+�J\�m\��\�\�ꫯ\�\�޺uk��׼\�5�n�\�3\�d\�\����\�{\�\'Fl�\�q\�\���=\�\�\�\��븽`^\�]1\�3\����ۥ��kn\����\�R�/�׋�x�\�x(�;�W7L�\�l�	\0\�/\�{�(�T�P(W/˖\��T����\��7J��\��\�1���\�m\���r\�_\�&{r��\�\�/�|\�ƍ7r�\�\�|;ε�\�\�y#yNS��#�<2��\��}�6�n�\�#�$\�?�|\�F �����\�{l��\�g�}\�s�Kn���\�\�nKn�\�\�MozS�ַ�5{~����o}k�\�\����\� ��\�9\�\�\�x#$��=\rAQ\Z�Ѳ\�Ȯreb��\�ȑ�����m�\�\0\�G�4���1S\�U�~&.�MO\Z�\n\�R\�\��\rPW�:+R\�yP�\�I�ns�&{rw\'N|c{{{O\Z6l߾}K]O���\��&}le\�\�\�\�\�\�xK���M\\b樥o\�}\�{_r��\�\Z�V�\\�<�\�S\�\�1\"\�\�\�4\��\�O\��C�u�\�=h(�۵k\�f\�ӟ�t��H^=o_��cYߛ\'3\���B����\�q�b\�E��#\rV����\�[�\����#�6�\�,W\�\0�/4�+cy^\\\�!m���\�\"\�LR\�,=V�Ns4G��w�˵Sg�T?Y(W��D�4V�M\�	\�bq\�\�ի\�mٲe\�%�\\�h\�\���ێ\�LS�|�\\.g��\�Gw���\"\�x㍻�\�\�)n#D�_��\�R�h \��k��&��:\�3�٦���Z��̞=;{NOOOv�/׋\�\����\�\Z�\�i\Z\�\�s,�7Cv�]]���^�\�L�^���\�_Q3d�ߗ��lֹoV*\�\��?�\�\�R>�	\080\�){�x\�ˋ�Kr\��E3\�\�\�V\�ra�\�\�P��8B\"�\�ҷCyPhF\�\�\��\"@Ej\�=�ۿ�\�\�\�9��/�=��듩S�\�M�ԧ>�۶�7o\���/_�\�~\�;\�\��}_|q\�FÇ\�\�\�9\�K�>����i�\��\�U1\�\�\�l\�n��\��\�\�(\�sʕ�\��\�\�\�\��\�\����\�o�~�\�~q�ɓ\'\�M\�V\0��{|�f�\�\�,�\�EfΜ�}}\�w6\\:�\���1\�BEp\�/\�\��9�LQ�8\�)n#�\�o�\�/$W]uU�ؚ5k�\�U�V\�\nQ#�\����k\��l9^�63�\�u�kg\�,{g\Z�\�\��&Kuz\�ƅcv^ �v\�h�\�\�m\�\�L�p0\�|�\���L\�U=\0�l$BS\\�!�-�s�n�ᆆ�:o~�`\�?\�\�K�\���BS�\�X�7cƌ䵯}m\����\��\�\�\�\���\�\��n�L/��ߡ\�GC��q���\�g�U;g\�\�=6\�\�>��\\]W\�\��\�\�N\�\�wڹL�\����3Rc 4����m:\����7iҤu�;YU\0á)&J�p�_�<_��vS~nQ,��ۿ���ɂN�{#E���\�K�\'�|�\��R1�t\�\�&��\�=�*?�*�0E�����u\�\�\�$�\�Ǆ�����/q^c\\�%.���\�c�)�\��\�n\��\�cYo�T]��;\�\����9ڤ\�Q-5\�\�\�\�ci\�\\�J�,\�x��\0h�\�\�6�P(d�dҤI{\\\"�\�\"0E���@\���\�g\�-\�E!\Z����>:�\�\'>1\�e\�c4�\�\�C\�hZ�7\�Ņ⪚q�\�ᄮQ�ޝ�\��p\�M�78E`�5kV�yL�\�\�ڎS\�\0\0\�xh\Z(H\�\�\�`\��?io\�QG�kVk�K�\"��\�>�ƿ\�\�\�\��B\�3N��T/�\�o�ɲ<\0�����Y�ᦣ>8\�9Nqq�\�{{���\�C�9L1\�@�%{\0@h�b\�)\�CO�����\0ܘEʗ\�\��u\�\�e�\�/�Jəg����y�$/���\n\0 4	M�+q�S��\��q,w\0�\�$41\�\�\�\��\�\�\�\�	K1�\�*y\0\0B�\�D\�hoo��4<\�0t[:�L\�־��kұ(\���\0 4	MB\0\0M�\�\0\0B�!4\0�\�dM\0\0 4B\0\0M�\�\0\0MB\0\0 4	M\0\0�\�$4\0\0B�\�\0\0M�\�\0\0B�!4\0�\�dM\0\0 4B\0\0M�\�\0\0MB\0\0 4	M\0\0�\�$4\0\0B�\�\0\0MB��\0\0�&Ch\0���R�+�glMC\�ˎJ\0\0E�-[�\�\r�Q0֭[wK\Z�uT\0�(R�V?{\�=�lz\�\�.#7\�������t|\�Q	\0\0�Lڨ��dɒ�biX�Sc���Q�	\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0h\Z�\��sB�.\0\0\0\0IEND�B`�',1);
/*!40000 ALTER TABLE `ACT_GE_BYTEARRAY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_GE_PROPERTY`
--

DROP TABLE IF EXISTS `ACT_GE_PROPERTY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_GE_PROPERTY` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_GE_PROPERTY`
--

LOCK TABLES `ACT_GE_PROPERTY` WRITE;
/*!40000 ALTER TABLE `ACT_GE_PROPERTY` DISABLE KEYS */;
INSERT INTO `ACT_GE_PROPERTY` VALUES ('cfg.execution-related-entities-count','false',1),('cfg.task-related-entities-count','false',1),('common.schema.version','6.2.0.0',1),('identitylink.schema.version','6.2.0.0',1),('job.schema.version','6.2.0.0',1),('next.dbid','10001',5),('schema.history','create(6.2.0.0)',1),('schema.version','6.2.0.0',1),('task.schema.version','6.2.0.0',1),('variable.schema.version','6.2.0.0',1);
/*!40000 ALTER TABLE `ACT_GE_PROPERTY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_ACTINST`
--

DROP TABLE IF EXISTS `ACT_HI_ACTINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_ACTINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_ACTINST`
--

LOCK TABLES `ACT_HI_ACTINST` WRITE;
/*!40000 ALTER TABLE `ACT_HI_ACTINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_ACTINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_ATTACHMENT`
--

DROP TABLE IF EXISTS `ACT_HI_ATTACHMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_ATTACHMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_ATTACHMENT`
--

LOCK TABLES `ACT_HI_ATTACHMENT` WRITE;
/*!40000 ALTER TABLE `ACT_HI_ATTACHMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_ATTACHMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_COMMENT`
--

DROP TABLE IF EXISTS `ACT_HI_COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_COMMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_COMMENT`
--

LOCK TABLES `ACT_HI_COMMENT` WRITE;
/*!40000 ALTER TABLE `ACT_HI_COMMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_COMMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_DETAIL`
--

DROP TABLE IF EXISTS `ACT_HI_DETAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_DETAIL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_DETAIL`
--

LOCK TABLES `ACT_HI_DETAIL` WRITE;
/*!40000 ALTER TABLE `ACT_HI_DETAIL` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_DETAIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_IDENTITYLINK`
--

DROP TABLE IF EXISTS `ACT_HI_IDENTITYLINK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_IDENTITYLINK`
--

LOCK TABLES `ACT_HI_IDENTITYLINK` WRITE;
/*!40000 ALTER TABLE `ACT_HI_IDENTITYLINK` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_IDENTITYLINK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_PROCINST`
--

DROP TABLE IF EXISTS `ACT_HI_PROCINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_PROCINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_PROCINST`
--

LOCK TABLES `ACT_HI_PROCINST` WRITE;
/*!40000 ALTER TABLE `ACT_HI_PROCINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_PROCINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_TASKINST`
--

DROP TABLE IF EXISTS `ACT_HI_TASKINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_TASKINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_TASKINST`
--

LOCK TABLES `ACT_HI_TASKINST` WRITE;
/*!40000 ALTER TABLE `ACT_HI_TASKINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_TASKINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_HI_VARINST`
--

DROP TABLE IF EXISTS `ACT_HI_VARINST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_HI_VARINST` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT '1',
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_VAR_SCOPE_ID_TYPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_VAR_SUB_ID_TYPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_EXE` (`EXECUTION_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_HI_VARINST`
--

LOCK TABLES `ACT_HI_VARINST` WRITE;
/*!40000 ALTER TABLE `ACT_HI_VARINST` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_HI_VARINST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_BYTEARRAY`
--

DROP TABLE IF EXISTS `ACT_ID_BYTEARRAY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_BYTEARRAY` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_BYTEARRAY`
--

LOCK TABLES `ACT_ID_BYTEARRAY` WRITE;
/*!40000 ALTER TABLE `ACT_ID_BYTEARRAY` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_BYTEARRAY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_GROUP`
--

DROP TABLE IF EXISTS `ACT_ID_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_GROUP` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_GROUP`
--

LOCK TABLES `ACT_ID_GROUP` WRITE;
/*!40000 ALTER TABLE `ACT_ID_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_INFO`
--

DROP TABLE IF EXISTS `ACT_ID_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_INFO`
--

LOCK TABLES `ACT_ID_INFO` WRITE;
/*!40000 ALTER TABLE `ACT_ID_INFO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_MEMBERSHIP`
--

DROP TABLE IF EXISTS `ACT_ID_MEMBERSHIP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_MEMBERSHIP` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `ACT_ID_GROUP` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `ACT_ID_USER` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_MEMBERSHIP`
--

LOCK TABLES `ACT_ID_MEMBERSHIP` WRITE;
/*!40000 ALTER TABLE `ACT_ID_MEMBERSHIP` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_MEMBERSHIP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_PRIV`
--

DROP TABLE IF EXISTS `ACT_ID_PRIV`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_PRIV` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_PRIV`
--

LOCK TABLES `ACT_ID_PRIV` WRITE;
/*!40000 ALTER TABLE `ACT_ID_PRIV` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_PRIV` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_PRIV_MAPPING`
--

DROP TABLE IF EXISTS `ACT_ID_PRIV_MAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_PRIV_MAPPING` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PRIV_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_PRIV_MAPPING` (`PRIV_ID_`),
  KEY `ACT_IDX_PRIV_USER` (`USER_ID_`),
  KEY `ACT_IDX_PRIV_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_PRIV_MAPPING` FOREIGN KEY (`PRIV_ID_`) REFERENCES `ACT_ID_PRIV` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_PRIV_MAPPING`
--

LOCK TABLES `ACT_ID_PRIV_MAPPING` WRITE;
/*!40000 ALTER TABLE `ACT_ID_PRIV_MAPPING` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_PRIV_MAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_PROPERTY`
--

DROP TABLE IF EXISTS `ACT_ID_PROPERTY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_PROPERTY` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_PROPERTY`
--

LOCK TABLES `ACT_ID_PROPERTY` WRITE;
/*!40000 ALTER TABLE `ACT_ID_PROPERTY` DISABLE KEYS */;
INSERT INTO `ACT_ID_PROPERTY` VALUES ('schema.version','6.2.0.0',1);
/*!40000 ALTER TABLE `ACT_ID_PROPERTY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_TOKEN`
--

DROP TABLE IF EXISTS `ACT_ID_TOKEN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_TOKEN` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TOKEN_VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOKEN_DATE_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `IP_ADDRESS_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_AGENT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TOKEN_DATA_` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_TOKEN`
--

LOCK TABLES `ACT_ID_TOKEN` WRITE;
/*!40000 ALTER TABLE `ACT_ID_TOKEN` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_TOKEN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_ID_USER`
--

DROP TABLE IF EXISTS `ACT_ID_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_ID_USER` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_ID_USER`
--

LOCK TABLES `ACT_ID_USER` WRITE;
/*!40000 ALTER TABLE `ACT_ID_USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_ID_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_PROCDEF_INFO`
--

DROP TABLE IF EXISTS `ACT_PROCDEF_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_PROCDEF_INFO` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_PROCDEF_INFO`
--

LOCK TABLES `ACT_PROCDEF_INFO` WRITE;
/*!40000 ALTER TABLE `ACT_PROCDEF_INFO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_PROCDEF_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RE_DEPLOYMENT`
--

DROP TABLE IF EXISTS `ACT_RE_DEPLOYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RE_DEPLOYMENT` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RE_DEPLOYMENT`
--

LOCK TABLES `ACT_RE_DEPLOYMENT` WRITE;
/*!40000 ALTER TABLE `ACT_RE_DEPLOYMENT` DISABLE KEYS */;
INSERT INTO `ACT_RE_DEPLOYMENT` VALUES ('1','SpringAutoDeployment',NULL,NULL,'','2018-01-28 06:53:39.856',NULL),('2501','SpringAutoDeployment',NULL,NULL,'','2018-02-27 03:53:21.641',NULL),('5001','SpringAutoDeployment',NULL,NULL,'','2018-03-19 07:58:26.205',NULL),('7501','SpringAutoDeployment',NULL,NULL,'','2018-03-19 09:49:53.268',NULL);
/*!40000 ALTER TABLE `ACT_RE_DEPLOYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RE_MODEL`
--

DROP TABLE IF EXISTS `ACT_RE_MODEL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RE_MODEL` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `ACT_RE_DEPLOYMENT` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RE_MODEL`
--

LOCK TABLES `ACT_RE_MODEL` WRITE;
/*!40000 ALTER TABLE `ACT_RE_MODEL` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RE_MODEL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RE_PROCDEF`
--

DROP TABLE IF EXISTS `ACT_RE_PROCDEF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RE_PROCDEF` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RE_PROCDEF`
--

LOCK TABLES `ACT_RE_PROCDEF` WRITE;
/*!40000 ALTER TABLE `ACT_RE_PROCDEF` DISABLE KEYS */;
INSERT INTO `ACT_RE_PROCDEF` VALUES ('Expense:1:4',1,'http://www.flowable.org/processdef','ExpenseProcess','Expense',1,'1','/home/frank/Workspace/cebrains/ceb-hrc/hrc-admin/target/classes/processes/ExpenseProcess.bpmn20.xml','/home/frank/Workspace/cebrains/ceb-hrc/hrc-admin/target/classes/processes/ExpenseProcess.Expense.png','报销流程',0,1,1,'',NULL),('Expense:2:2504',1,'http://www.flowable.org/processdef','ExpenseProcess','Expense',2,'2501','/home/frank/Workspace/cebrains/Server/hrc/hrc-admin/target/classes/processes/ExpenseProcess.bpmn20.xml','/home/frank/Workspace/cebrains/Server/hrc/hrc-admin/target/classes/processes/ExpenseProcess.Expense.png','报销流程',0,1,1,'',NULL),('Expense:3:5004',1,'http://www.flowable.org/processdef','ExpenseProcess','Expense',3,'5001','/home/frank/Workspace/cebrains/ceb-hrc/hrc-admin/target/classes/processes/ExpenseProcess.bpmn20.xml','/home/frank/Workspace/cebrains/ceb-hrc/hrc-admin/target/classes/processes/ExpenseProcess.Expense.png','报销流程',0,1,1,'',NULL),('Expense:4:7504',1,'http://www.flowable.org/processdef','ExpenseProcess','Expense',4,'7501','/home/frank/Workspace/cebrains/Server/hrc/hrc-admin/target/classes/processes/ExpenseProcess.bpmn20.xml','/home/frank/Workspace/cebrains/Server/hrc/hrc-admin/target/classes/processes/ExpenseProcess.Expense.png','报销流程',0,1,1,'',NULL);
/*!40000 ALTER TABLE `ACT_RE_PROCDEF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_DEADLETTER_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_DEADLETTER_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_DEADLETTER_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_DEADLETTER_JOB`
--

LOCK TABLES `ACT_RU_DEADLETTER_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_DEADLETTER_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_DEADLETTER_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_EVENT_SUBSCR`
--

DROP TABLE IF EXISTS `ACT_RU_EVENT_SUBSCR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_EVENT_SUBSCR` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_EVENT_SUBSCR`
--

LOCK TABLES `ACT_RU_EVENT_SUBSCR` WRITE;
/*!40000 ALTER TABLE `ACT_RU_EVENT_SUBSCR` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_EVENT_SUBSCR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_EXECUTION`
--

DROP TABLE IF EXISTS `ACT_RU_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_EXECUTION` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_MI_ROOT_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `EVT_SUBSCR_COUNT_` int(11) DEFAULT NULL,
  `TASK_COUNT_` int(11) DEFAULT NULL,
  `JOB_COUNT_` int(11) DEFAULT NULL,
  `TIMER_JOB_COUNT_` int(11) DEFAULT NULL,
  `SUSP_JOB_COUNT_` int(11) DEFAULT NULL,
  `DEADLETTER_JOB_COUNT_` int(11) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  `CALLBACK_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CALLBACK_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_EXECUTION`
--

LOCK TABLES `ACT_RU_EXECUTION` WRITE;
/*!40000 ALTER TABLE `ACT_RU_EXECUTION` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_HISTORY_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_HISTORY_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_HISTORY_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `ADV_HANDLER_CFG_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_HISTORY_JOB`
--

LOCK TABLES `ACT_RU_HISTORY_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_HISTORY_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_HISTORY_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_IDENTITYLINK`
--

DROP TABLE IF EXISTS `ACT_RU_IDENTITYLINK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_IDENTITYLINK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `ACT_RU_TASK` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_IDENTITYLINK`
--

LOCK TABLES `ACT_RU_IDENTITYLINK` WRITE;
/*!40000 ALTER TABLE `ACT_RU_IDENTITYLINK` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_IDENTITYLINK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_FK_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_JOB`
--

LOCK TABLES `ACT_RU_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_SUSPENDED_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_SUSPENDED_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_SUSPENDED_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_SUSPENDED_JOB`
--

LOCK TABLES `ACT_RU_SUSPENDED_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_SUSPENDED_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_SUSPENDED_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_TASK`
--

DROP TABLE IF EXISTS `ACT_RU_TASK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_TASK` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_DEFINITION_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_IDX_TASK_SCOPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TASK_SUB_SCOPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_TASK_SCOPE_DEF` (`SCOPE_DEFINITION_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_TASK`
--

LOCK TABLES `ACT_RU_TASK` WRITE;
/*!40000 ALTER TABLE `ACT_RU_TASK` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_TASK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_TIMER_JOB`
--

DROP TABLE IF EXISTS `ACT_RU_TIMER_JOB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_TIMER_JOB` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_TIMER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  KEY `ACT_FK_TIMER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `ACT_RE_PROCDEF` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_TIMER_JOB`
--

LOCK TABLES `ACT_RU_TIMER_JOB` WRITE;
/*!40000 ALTER TABLE `ACT_RU_TIMER_JOB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_TIMER_JOB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ACT_RU_VARIABLE`
--

DROP TABLE IF EXISTS `ACT_RU_VARIABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACT_RU_VARIABLE` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUB_SCOPE_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SCOPE_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_RU_VAR_SCOPE_ID_TYPE` (`SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_IDX_RU_VAR_SUB_ID_TYPE` (`SUB_SCOPE_ID_`,`SCOPE_TYPE_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `ACT_GE_BYTEARRAY` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `ACT_RU_EXECUTION` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACT_RU_VARIABLE`
--

LOCK TABLES `ACT_RU_VARIABLE` WRITE;
/*!40000 ALTER TABLE `ACT_RU_VARIABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACT_RU_VARIABLE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-22 19:54:46
