/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.AssetDetailsModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class AssetController extends BaseController {

    public AssetController() {
        log = getLogger(this.getClass());
    }

    public boolean registerAsset(String locationName, String assetCode, String buildingName, int memberCode, int rateId, String geoLocation, String photo, String note) {
        boolean isCreate = false;
        final String methodName = "registerInstalationAsset";
        start(methodName);
        String sql = "INSERT INTO masterassetlocation "
                + "(locationname, assetcode, buildingname, membercode, rateid, geolocation, photo, note) "
                + "VALUES(:locationName, :assetCode, :buildingName, :memberCode, :rateId, :geoLocation, :photo, :note);";
        try ( Handle handle = getHandle()) {
            int result = handle.createUpdate(sql)
                    .bind("locationName", locationName)
                    .bind("assetCode", assetCode)
                    .bind("buildingName", buildingName)
                    .bind("memberCode", memberCode)
                    .bind("rateId", rateId)
                    .bind("geoLocation", geoLocation)
                    .bind("photo", photo)
                    .bind("note", note)
                    .execute();

            if (result != 0) {
                isCreate = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        completed(methodName);
        return isCreate;
    }

    public List<AssetDetailsModel> findAsset(String assetCode) {

        List<AssetDetailsModel> output = new ArrayList<>();
        final String methodName = "findAssetData";
        start(methodName);

        String sql = "SELECT ASSET.assetcode, ASSET.assetname, MODEL.assettype, ASSET.manufacture, ASSET.model, VENDOR.vendorname, ASSET.note, ASSET.created "
                + " FROM masterasset ASSET "
                + " INNER JOIN mastertype MODEL ON MODEL.typeid = ASSET.typeid "
                + " INNER JOIN mastervendor VENDOR ON VENDOR.vendorid = ASSET.vendorid "
                + " WHERE ASSET.assetcode = :assetCode";

        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql).bind("assetCode", assetCode).mapToBean(AssetDetailsModel.class).list();
        } catch (SQLException ex) {
            Logger.getLogger(AssetController.class.getName()).log(Level.SEVERE, null, ex);
        }

        completed(methodName);
        return output;
    }

    public List<AssetDetailsModel> getAllAsset() {
        List<AssetDetailsModel> output = new ArrayList<>();
        final String methodName = "getListOfAsset";
        start(methodName);

        String sql = "SELECT ASSET.assetcode, ASSET.assetname, MODEL.assettype, ASSET.manufacture, ASSET.model, VENDOR.vendorname, ASSET.note, ASSET.created "
                + " FROM masterasset ASSET "
                + " INNER JOIN mastertype MODEL ON MODEL.typeid = ASSET.typeid"
                + " INNER JOIN mastervendor VENDOR ON VENDOR.vendorid = ASSET.vendorid";

        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql).mapToBean(AssetDetailsModel.class).list();
        } catch (SQLException ex) {
            Logger.getLogger(AssetController.class.getName()).log(Level.SEVERE, null, ex);
        }

        completed(methodName);
        return output;
    }

}
