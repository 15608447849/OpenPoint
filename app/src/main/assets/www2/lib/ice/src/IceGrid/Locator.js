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
// Generated from file `Locator.ice'
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
    var __M = require("../Ice/ModuleRegistry").Ice.__M;
    var Ice = __M.require(module, 
    [
        "../Ice/Object",
        "../Ice/ObjectPrx",
        "../Ice/Operation",
        "../Ice/Long",
        "../Ice/HashMap",
        "../Ice/HashUtil",
        "../Ice/ArrayUtil",
        "../Ice/StreamHelpers",
        "../Ice/Locator"
    ]).Ice;
    
    var Slice = Ice.Slice;
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */

    var IceGrid = __M.module("IceGrid");
/* slice2js browser-bundle-skip-end */

    /**
     * The IceGrid locator interface provides access to the {@link Query}
     * and {@link Registry} object of the IceGrid registry.
     * 
     * @see Query
     * @see Registry
     * 
     **/
    IceGrid.Locator = Slice.defineObject(
        undefined,
        Ice.Object,
        [
            Ice.Locator
        ], 2,
        [
            "::Ice::Locator",
            "::Ice::Object",
            "::IceGrid::Locator"
        ],
        -1, undefined, undefined, false);

    IceGrid.LocatorPrx = Slice.defineProxy(Ice.ObjectPrx, IceGrid.Locator.ice_staticId, [
        Ice.LocatorPrx]);

    Slice.defineOperations(IceGrid.Locator, IceGrid.LocatorPrx,
    {
        "getLocalRegistry": [, 2, 2, , , ["IceGrid.RegistryPrx"], , , , , ],
        "getLocalQuery": [, 2, 2, , , ["IceGrid.QueryPrx"], , , , , ]
    });
/* slice2js browser-bundle-skip */
    exports.IceGrid = IceGrid;
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */
}
(typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? module : undefined,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? require : this.Ice.__require,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? exports : this));
/* slice2js browser-bundle-skip-end */