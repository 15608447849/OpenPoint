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
    var __M = require("ModuleRegistry").Ice.__M;
    var Ice = __M.require(module, 
    [
        "../Ice/Object",
        "../Ice/ObjectPrx",
        "../Ice/Operation",
        "../Ice/Exception",
        "../Ice/Long",
        "../Ice/HashMap",
        "../Ice/HashUtil",
        "../Ice/ArrayUtil",
        "../Ice/StreamHelpers",
        "../Ice/Identity",
        "../Ice/ProcessF"
    ]).Ice;
    
    var Slice = Ice.Slice;
/* slice2js browser-bundle-skip-end */

    /**
     * This exception is raised if an adapter cannot be found.
     * 
     **/
    Ice.AdapterNotFoundException = Slice.defineUserException(
        function(_cause)
        {
            Ice.UserException.call(this, _cause);
        },
        Ice.UserException,
        "Ice::AdapterNotFoundException",
        undefined, undefined,
        false,
        false);

    /**
     * This exception is raised if the replica group provided by the
     * server is invalid.
     * 
     **/
    Ice.InvalidReplicaGroupIdException = Slice.defineUserException(
        function(_cause)
        {
            Ice.UserException.call(this, _cause);
        },
        Ice.UserException,
        "Ice::InvalidReplicaGroupIdException",
        undefined, undefined,
        false,
        false);

    /**
     * This exception is raised if a server tries to set endpoints for
     * an adapter that is already active.
     * 
     **/
    Ice.AdapterAlreadyActiveException = Slice.defineUserException(
        function(_cause)
        {
            Ice.UserException.call(this, _cause);
        },
        Ice.UserException,
        "Ice::AdapterAlreadyActiveException",
        undefined, undefined,
        false,
        false);

    /**
     * This exception is raised if an object cannot be found.
     * 
     **/
    Ice.ObjectNotFoundException = Slice.defineUserException(
        function(_cause)
        {
            Ice.UserException.call(this, _cause);
        },
        Ice.UserException,
        "Ice::ObjectNotFoundException",
        undefined, undefined,
        false,
        false);

    /**
     * This exception is raised if a server cannot be found.
     * 
     **/
    Ice.ServerNotFoundException = Slice.defineUserException(
        function(_cause)
        {
            Ice.UserException.call(this, _cause);
        },
        Ice.UserException,
        "Ice::ServerNotFoundException",
        undefined, undefined,
        false,
        false);

    /**
     * The Ice locator interface. This interface is used by clients to
     * lookup adapters and objects. It is also used by servers to get the
     * locator registry proxy.
     * 
     * <p class="Note">The {@link Locator} interface is intended to be used by
     * Ice internals and by locator implementations. Regular user code
     * should not attempt to use any functionality of this interface
     * directly.
     * 
     **/
    Ice.Locator = Slice.defineObject(
        undefined,
        Ice.Object, undefined, 0,
        [
            "::Ice::Locator",
            "::Ice::Object"
        ],
        -1, undefined, undefined, false);

    Ice.LocatorPrx = Slice.defineProxy(Ice.ObjectPrx, Ice.Locator.ice_staticId, undefined);

    Slice.defineOperations(Ice.Locator, Ice.LocatorPrx,
    {
        "findObjectById": [, 2, 1, 1, , [9], [[Ice.Identity]], , 
        [
            Ice.ObjectNotFoundException
        ], , ],
        "findAdapterById": [, 2, 1, 1, , [9], [[7]], , 
        [
            Ice.AdapterNotFoundException
        ], , ],
        "getRegistry": [, 2, 1, , , ["Ice.LocatorRegistryPrx"], , , , , ]
    });

    /**
     * The Ice locator registry interface. This interface is used by
     * servers to register adapter endpoints with the locator.
     * 
     * <p class="Note"> The {@link LocatorRegistry} interface is intended to be used
     * by Ice internals and by locator implementations. Regular user
     * code should not attempt to use any functionality of this interface
     * directly.
     * 
     **/
    Ice.LocatorRegistry = Slice.defineObject(
        undefined,
        Ice.Object, undefined, 0,
        [
            "::Ice::LocatorRegistry",
            "::Ice::Object"
        ],
        -1, undefined, undefined, false);

    Ice.LocatorRegistryPrx = Slice.defineProxy(Ice.ObjectPrx, Ice.LocatorRegistry.ice_staticId, undefined);

    Slice.defineOperations(Ice.LocatorRegistry, Ice.LocatorRegistryPrx,
    {
        "setAdapterDirectProxy": [, 2, 2, 1, , , [[7], [9]], , 
        [
            Ice.AdapterAlreadyActiveException,
            Ice.AdapterNotFoundException
        ], , ],
        "setReplicatedAdapterDirectProxy": [, 2, 2, 1, , , [[7], [7], [9]], , 
        [
            Ice.AdapterAlreadyActiveException,
            Ice.AdapterNotFoundException,
            Ice.InvalidReplicaGroupIdException
        ], , ],
        "setServerProcessProxy": [, 2, 2, 1, , , [[7], ["Ice.ProcessPrx"]], , 
        [
            Ice.ServerNotFoundException
        ], , ]
    });

    /**
     * This inferface should be implemented by services implementing the
     * Ice::Locator interface. It should be advertised through an Ice
     * object with the identity `Ice/LocatorFinder'. This allows clients
     * to retrieve the locator proxy with just the endpoint information of
     * the service.
     * 
     **/
    Ice.LocatorFinder = Slice.defineObject(
        undefined,
        Ice.Object, undefined, 0,
        [
            "::Ice::LocatorFinder",
            "::Ice::Object"
        ],
        -1, undefined, undefined, false);

    Ice.LocatorFinderPrx = Slice.defineProxy(Ice.ObjectPrx, Ice.LocatorFinder.ice_staticId, undefined);

    Slice.defineOperations(Ice.LocatorFinder, Ice.LocatorFinderPrx,
    {
        "getLocator": [, , , , , ["Ice.LocatorPrx"], , , , , ]
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
