/*
 * Copyright (C) 2015 Swift Navigation Inc.
 * Contact: Gareth McMullin <gareth@swiftnav.com>
 * Contact: Bhaskar Mookerji <mookerji@swiftnav.com>
 *
 * This source is subject to the license found in the file 'LICENSE' which must
 * be be distributed together with this source. All other rights reserved.
 *
 * THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 */

package com.swiftnav.sbp.orientation;

import java.math.BigInteger;

import com.swiftnav.sbp.SBPMessage;
import com.swiftnav.sbp.SBPBinaryException;
import com.swiftnav.sbp.SBPStruct;

import org.json.JSONObject;
import org.json.JSONArray;


/** SBP class for message MSG_ORIENT_EULER (0x0221).
 *
 * You can have MSG_ORIENT_EULER inherent its fields directly from
 * an inherited SBP object, or construct it inline using a dict of its
 * fields.
 *
 * This message reports the yaw, pitch, and roll angles of the vehicle body frame.
 * The rotations should applied intrinsically in the order yaw, pitch, and roll 
 * in order to rotate the from a frame aligned with the local-level NED frame 
 * to the vehicle body frame. */

public class MsgOrientEuler extends SBPMessage {
    public static final int TYPE = 0x0221;

    
    /** GPS Time of Week */
    public long tow;
    
    /** rotation about the forward axis of the vehicle */
    public int roll;
    
    /** rotation about the rightward axis of the vehicle */
    public int pitch;
    
    /** rotation about the downward axis of the vehicle */
    public int yaw;
    
    /** Estimated standard deviation of roll */
    public float var_roll;
    
    /** Estimated standard deviation of pitch */
    public float var_pitch;
    
    /** Estimated standard deviation of yaw */
    public float var_yaw;
    
    /** Status flags */
    public int flags;
    

    public MsgOrientEuler (int sender) { super(sender, TYPE); }
    public MsgOrientEuler () { super(TYPE); }
    public MsgOrientEuler (SBPMessage msg) throws SBPBinaryException {
        super(msg);
        assert msg.type != TYPE;
    }

    @Override
    protected void parse(Parser parser) throws SBPBinaryException {
        /* Parse fields from binary */
        tow = parser.getU32();
        roll = parser.getS16();
        pitch = parser.getS16();
        yaw = parser.getS16();
        var_roll = parser.getFloat();
        var_pitch = parser.getFloat();
        var_yaw = parser.getFloat();
        flags = parser.getU8();
    }

    @Override
    protected void build(Builder builder) {
        builder.putU32(tow);
        builder.putS16(roll);
        builder.putS16(pitch);
        builder.putS16(yaw);
        builder.putFloat(var_roll);
        builder.putFloat(var_pitch);
        builder.putFloat(var_yaw);
        builder.putU8(flags);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put("tow", tow);
        obj.put("roll", roll);
        obj.put("pitch", pitch);
        obj.put("yaw", yaw);
        obj.put("var_roll", var_roll);
        obj.put("var_pitch", var_pitch);
        obj.put("var_yaw", var_yaw);
        obj.put("flags", flags);
        return obj;
    }
}