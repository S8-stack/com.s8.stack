

/**
 * @type{Intl.NumberFormat[]}
 */
export const S8_NumberFormats = new Array(256);


/** STD2 */
S8_NumberFormats[0x22] = new Intl.NumberFormat('en-US', { 
    notation: "standard",
    maximumFractionDigits: 2,
    useGrouping: false
});

/** STD3 */
S8_NumberFormats[0x23] = new Intl.NumberFormat('en-US', { 
    notation: "standard",
    maximumFractionDigits: 3,
    useGrouping: false
});

/** STD4 */
S8_NumberFormats[0x24] = new Intl.NumberFormat('en-US', { 
    notation: "standard",
    maximumFractionDigits: 4,
    useGrouping: false
});

/** STD6 */
S8_NumberFormats[0x25] = new Intl.NumberFormat('en-US', { 
    notation: "standard",
    maximumFractionDigits: 6,
    useGrouping: false
});

/** SCI2 */
S8_NumberFormats[0x42] = new Intl.NumberFormat('en-US', { 
    notation: "scientific",
    maximumSignificantDigits: 2,
    useGrouping: false
});


/** SCI3 */
S8_NumberFormats[0x43] = new Intl.NumberFormat('en-US', { 
    notation: "scientific",
    maximumSignificantDigits: 3,
    useGrouping: false
});


/** SCI4 */
S8_NumberFormats[0x44] = new Intl.NumberFormat('en-US', { 
    notation: "scientific",
    maximumSignificantDigits: 4,
    useGrouping: false
});

/** SCI6 */
S8_NumberFormats[0x46] = new Intl.NumberFormat('en-US', { 
    notation: "scientific",
    maximumSignificantDigits: 6,
    useGrouping: false
});
