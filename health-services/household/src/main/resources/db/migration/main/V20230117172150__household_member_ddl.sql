CREATE TABLE IF NOT EXISTS HOUSEHOLD_MEMBER
(
    individualId        character varying(64),
    householdId         character varying(64),
    isHeadOfHousehold   boolean,
    additionalDetails   jsonb,
    createdBy           character varying(64),
    createdTime         bigint,
    lastModifiedBy      character varying(64),
    lastModifiedTime    bigint,
    rowVersion          bigint,
    isDeleted           boolean
);