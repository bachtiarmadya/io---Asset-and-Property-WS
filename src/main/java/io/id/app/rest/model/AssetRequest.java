/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.model;

/**
 *
 * @author permadi
 */
public class AssetRequest {
    
    private String assetCode;

    public AssetRequest() {
    }

    public AssetRequest(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }
    
    
    
}
