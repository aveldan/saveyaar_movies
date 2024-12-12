import React from "react";
import { StyleSheet, TouchableOpacity, View } from "react-native";
import TextComponent from "./TextComponents";
import { BlueLineBigIcon, BlueLineSmallIcon, WhiteLine } from "@/assets/svg";

interface TabComp {
    text: string,
    handleOnPress: () => void,
    isActive: Boolean
};

export interface TabCompArr {
    tabs: TabComp[],
    whiteline?: Boolean
};

export default function MidTabComponent ({tabs, whiteline = false}: TabCompArr) {
    return (
        <View style={styles.container}>
            <View style={styles.rowContainer}>
                {
                    tabs.map((tab) => (
                        <TouchableOpacity style={styles.tab} onPress={tab.handleOnPress} key={tab.text}>
                            <TextComponent 
                                text={tab.text}
                                size={15}
                                color={tab.isActive? "#FFFFFF" : "#FFFFFF99"}
                            />
                            {tab.isActive && !whiteline && <BlueLineSmallIcon />}
                            {tab.isActive && whiteline && <BlueLineBigIcon />}
                        </TouchableOpacity>
                    ))
                }
            </View>
            {whiteline && <WhiteLine />}
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: 'column',
        paddingTop: 10,
        paddingBottom: 20,
    },
    rowContainer: {
        backgroundColor: 'transparent',
        flexDirection: 'row',
        justifyContent: 'space-evenly',
    },
    tab:{
        alignItems: 'center',
        justifyContent: 'center',
    }
});