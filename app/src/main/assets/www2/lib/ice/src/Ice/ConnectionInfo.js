// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `ConnectionInfo.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

/* slice2js browser-bundle-skip */
(function(module, require, exports)
{
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */
    var __M = require("ModuleRegistry").Ice.__M;
    var Ice = __M.require(module, 
    [
        "../Ice/Object",
        "../Ice/ObjectPrx",
        "../Ice/Long",
        "../Ice/HashMap",
        "../Ice/HashUtil",
        "../Ice/ArrayUtil",
        "../Ice/StreamHelpers",
        "../Ice/Connection"
    ]).Ice;
    
    var Slice = Ice.Slice;
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */

    var IceSSL = __M.module("IceSSL");
/* slice2js browser-bundle-skip-end */

    /**
     * Provides access to the connection details of an SSL connection
     * 
     **/
    IceSSL.ConnectionInfo = Slice.defineLocalObject(
        function(incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort, cipher, certs, verified)
        {
            Ice.IPConnectionInfo.call(this, incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort);
            this.cipher = cipher !== undefined ? cipher : "";
            this.certs = certs !== undefined ? certs : null;
            this.verified = verified !== undefined ? verified : false;
        },
        Ice.IPConnectionInfo);

    /**
     * Provides access to the connection details of a secure WebSocket connection
     * 
     **/
    IceSSL.WSSConnectionInfo = Slice.defineLocalObject(
        function(incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort, cipher, certs, verified, headers)
        {
            IceSSL.ConnectionInfo.call(this, incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort, cipher, certs, verified);
            this.headers = headers !== undefined ? headers : null;
        },
        IceSSL.ConnectionInfo);
/* slice2js browser-bundle-skip */
    exports.IceSSL = IceSSL;
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */
}
(typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? module : undefined,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? require : this.Ice.__require,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? exports : this));
/* slice2js browser-bundle-skip-end */