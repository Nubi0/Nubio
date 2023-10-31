import { createSlice } from "@reduxjs/toolkit";

const MapSlice = createSlice({
  name: "MapSlice",
  initialState: {
    keyWord: null,
    latitude: null,
    longitude: null,
    start: {},
    end: {},
    startName: null,
    endName: null,
    listIsOpen: false,
    searchMarker: [],
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
    setListIsOpen: (state, action) => {
      state.listIsOpen = action.payload;
    },
    setSearchMarker: (state, action) => {
      state.searchMarker = action.payload;
    },
  },
});

export const {
  setkeyWord,
  setLatitude,
  setLongitude,
  setStartName,
  setEndName,
  setStart,
  setEnd,
  setListIsOpen,
  setSearchMarker,
} = MapSlice.actions;
export default MapSlice.reducer;
