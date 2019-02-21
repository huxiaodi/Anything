package com.dllg.ebpay.model.generator;

/**
 *   省级表
 *   pub_pay_node
 * @mbg.generated do_not_delete_during_merge
 */
public class PubPayNode {
    /**
     *   pub_pay_node.node_nodecode
     * @mbg.generated
     */
    private String nodeNodecode;

    /**
     *   pub_pay_node.node_nodename
     * @mbg.generated
     */
    private String nodeNodename;

    /**
     * @return the value of pub_pay_node.node_nodecode
     * @mbg.generated
     */
    public String getNodeNodecode() {
        return nodeNodecode;
    }

    /**
     * @param nodeNodecode the value for pub_pay_node.node_nodecode
     * @mbg.generated
     */
    public void setNodeNodecode(String nodeNodecode) {
        this.nodeNodecode = nodeNodecode == null ? null : nodeNodecode.trim();
    }

    /**
     * @return the value of pub_pay_node.node_nodename
     * @mbg.generated
     */
    public String getNodeNodename() {
        return nodeNodename;
    }

    /**
     * @param nodeNodename the value for pub_pay_node.node_nodename
     * @mbg.generated
     */
    public void setNodeNodename(String nodeNodename) {
        this.nodeNodename = nodeNodename == null ? null : nodeNodename.trim();
    }
}