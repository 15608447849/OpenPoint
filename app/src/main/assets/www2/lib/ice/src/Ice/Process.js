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
// Generated from file `Process.ice'
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
        "../Ice/Operation",
        "../Ice/Long",
        "../Ice/HashMap",
        "../Ice/HashUtil",
        "../Ice/ArrayUtil",
        "../Ice/StreamHelpers"
    ]).Ice;
    
    var Slice = Ice.Slice;
/* slice2js browser-bundle-skip-end */

    /**
     * An administrative interface for process management. Managed servers must
     * implement this interface.
     * 
     * <p class="Note">A servant implementing this interface is a potential target
     * for denial-of-service attacks, therefore proper security precautions
     * should be taken. For example, the servant can use a UUID to make its
     * identity harder to guess, and be registered in an object adapter with
     * a secured endpoint.
     * 
     **/
    Ice.Process = Slice.defineObject(
        undefined,
        Ice.Object, undefined, 1,
        [
            "::Ice::Object",
            "::Ice::Process"
        ],
        -1, undefined, undefined, false);

    Ice.ProcessPrx = Slice.defineProxy(Ice.ObjectPrx, Ice.Process.ice_staticId, undefined);

    Slice.defineOperations(Ice.Process, Ice.ProcessPrx,
    {
        "shutdown": [, , , , , , , , , , ],
        "writeMessage": [, , , , , , [[7], [3]], , , , ]
    });
/* slice2js browser-bundle-skip */
    exports.Ice = Ice;
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */
}
(typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? module : undefined,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? require : this.Ice.__require,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? exports : this));
/* slice2js browser-bundle-skip-end */