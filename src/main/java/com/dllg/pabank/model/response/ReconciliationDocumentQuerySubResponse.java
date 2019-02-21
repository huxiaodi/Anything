package com.dllg.pabank.model.response;

public class ReconciliationDocumentQuerySubResponse {
    /**
     * 文件名称
     */
    private String FileName;
    /**
     * 随机密码
     */
    private String RandomPassword;
    /**
     * 文件路径
     */
    private String FilePath;
    /**
     * 提取码
     */
    private String DrawCode;

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getRandomPassword() {
        return RandomPassword;
    }

    public void setRandomPassword(String randomPassword) {
        RandomPassword = randomPassword;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getDrawCode() {
        return DrawCode;
    }

    public void setDrawCode(String drawCode) {
        DrawCode = drawCode;
    }
}
