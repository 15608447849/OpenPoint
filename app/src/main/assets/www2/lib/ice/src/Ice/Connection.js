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
// Generated from file `Connection.ice'
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
        "../Ice/Struct",
        "../Ice/EnumBase",
        "../Ice/Long",
        "../Ice/HashMap",
        "../Ice/HashUtil",
        "../Ice/ArrayUtil",
        "../Ice/StreamHelpers",
        "../Ice/ObjectAdapterF",
        "../Ice/Identity",
        "../Ice/Endpoint"
    ]).Ice;
    
    var Slice = Ice.Slice;
/* slice2js browser-bundle-skip-end */

    /**
     * Base class providing access to the connection details. *
     **/
    Ice.ConnectionInfo = Slice.defineLocalObject(
        function(incoming, adapterName, connectionId, rcvSize, sndSize)
        {
            this.incoming = incoming !== undefined ? incoming : false;
            this.adapterName = adapterName !== undefined ? adapterName : "";
            this.connectionId = connectionId !== undefined ? connectionId : "";
            this.rcvSize = rcvSize !== undefined ? rcvSize : 0;
            this.sndSize = sndSize !== undefined ? sndSize : 0;
        });

    /**
     * An application can implement this interface to receive notifications when
     * a connection closes or receives a heartbeat message.
     * 
     * @see Connection#setCallback
     * 
     **/
    Ice.ConnectionCallback = Slice.defineLocalObject();

    Ice.ACMClose = Slice.defineEnum([
        ['CloseOff', 0], ['CloseOnIdle', 1], ['CloseOnInvocation', 2], ['CloseOnInvocationAndIdle', 3], ['CloseOnIdleForceful', 4]]);

    Ice.ACMHeartbeat = Slice.defineEnum([
        ['HeartbeatOff', 0], ['HeartbeatOnInvocation', 1], ['HeartbeatOnIdle', 2], ['HeartbeatAlways', 3]]);

    Ice.ACM = Slice.defineStruct(
        function(timeout, close, heartbeat)
        {
            this.timeout = timeout !== undefined ? timeout : 0;
            this.close = close !== undefined ? close : Ice.ACMClose.CloseOff;
            this.heartbeat = heartbeat !== undefined ? heartbeat : Ice.ACMHeartbeat.HeartbeatOff;
        },
        true);

    /**
     * The user-level interface to a connection.
     * 
     **/
    Ice.Connection = Slice.defineLocalObject();

    /**
     * Provides access to the connection details of an IP connection
     * 
     **/
    Ice.IPConnectionInfo = Slice.defineLocalObject(
        function(incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort)
        {
            Ice.ConnectionInfo.call(this, incoming, adapterName, connectionId, rcvSize, sndSize);
            this.localAddress = localAddress !== undefined ? localAddress : "";
            this.localPort = localPort !== undefined ? localPort : -1;
            this.remoteAddress = remoteAddress !== undefined ? remoteAddress : "";
            this.remotePort = remotePort !== undefined ? remotePort : -1;
        },
        Ice.ConnectionInfo);

    /**
     * Provides access to the connection details of a TCP connection
     * 
     **/
    Ice.TCPConnectionInfo = Slice.defineLocalObject(
        function(incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort)
        {
            Ice.IPConnectionInfo.call(this, incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort);
        },
        Ice.IPConnectionInfo);

    /**
     * Provides access to the connection details of a UDP connection
     * 
     **/
    Ice.UDPConnectionInfo = Slice.defineLocalObject(
        function(incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort, mcastAddress, mcastPort)
        {
            Ice.IPConnectionInfo.call(this, incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort);
            this.mcastAddress = mcastAddress !== undefined ? mcastAddress : "";
            this.mcastPort = mcastPort !== undefined ? mcastPort : -1;
        },
        Ice.IPConnectionInfo);
    Slice.defineDictionary(Ice, "HeaderDict", "HeaderDictHelper", "Ice.StringHelper", "Ice.StringHelper", false, undefined, undefined);

    /**
     * Provides access to the connection details of a WebSocket connection
     * 
     **/
    Ice.WSConnectionInfo = Slice.defineLocalObject(
        function(incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort, headers)
        {
            Ice.TCPConnectionInfo.call(this, incoming, adapterName, connectionId, rcvSize, sndSize, localAddress, localPort, remoteAddress, remotePort);
            this.headers = headers !== undefined ? headers : null;
        },
        Ice.TCPConnectionInfo);
/* slice2js browser-bundle-skip */
    exports.Ice = Ice;
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */
}
(typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? module : undefined,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? require : this.Ice.__require,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? exports : this));
/* slice2js browser-bundle-skip-end */