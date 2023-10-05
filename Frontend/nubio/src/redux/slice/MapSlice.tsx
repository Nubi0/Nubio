import { createSlice } from "@reduxjs/toolkit";

const MapSlice = createSlice({
  name: "MapSlice",
  initialState: {
    keyWord: null,
    latitude: null,
    longitude: null,
    shortTime: null,
    safeTime: null,
    start: {},
    end: {},
    startName: null,
    endName: null,
    safePlace: [],
    showShelters: false,
    markerList: [],
    messageMarkerList: [] as any,
  },
  reducers: {
    setkeyWord: (state, action) => {
      state.keyWord = action.payload;
    },
    setLatitude: (state, action) => {
      state.latitude = action.payload;
    },
    setLongitude: (state, action) => {
      state.longitude = action.payload;
    },
    setShortTime: (state, action) => {
      state.shortTime = action.payload;
    },
    setSafeTime: (state, action) => {
      state.safeTime = action.payload;
    },
    setStart: (state, action) => {
      state.start = action.payload;
    },
    setEnd: (state, action) => {
      state.end = action.payload;
    },
    setStartName: (state, action) => {
      state.startName = action.payload;
    },
    setEndName: (state, action) => {
      state.endName = action.payload;
    },
    setSafePlace: (state, action) => {
      state.safePlace = action.payload;
    },
    setShowShelters: (state, action) => {
      state.showShelters = action.payload;
    },
    setMarkerList: (state, action) => {
      state.markerList = action.payload;
    },
    setMessageMarkerList: (state, action) => {
      const payloads = Array.isArray(action.payload)
        ? action.payload
        : [action.payload];
      state.messageMarkerList.push(...payloads);
    },
  },
});

export const {
  setkeyWord,
  setLatitude,
  setLongitude,
  setShortTime,
  setSafeTime,
  setStartName,
  setEndName,
  setStart,
  setEnd,
  setSafePlace,
  setShowShelters,
  setMarkerList,
  setMessageMarkerList,
} = MapSlice.actions;
export default MapSlice.reducer;
