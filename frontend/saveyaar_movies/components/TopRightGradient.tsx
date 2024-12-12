import React from "react";
import { ImageBackground, StyleSheet, View } from "react-native";
import { StatusBar } from "expo-status-bar";

import { background } from "@/assets/images";

interface Props {
    children: React.ReactNode
};

export default function TopRightGradient({children}: Props) {

    return (
        <View style={styles.container}>
            <StatusBar  style= "light"/>
            <ImageBackground source={background} style={styles.conatinerImage}/>
            {children}
        </View>
    )
};


const styles = StyleSheet.create({
	container: {
        flex: 1,
        backgroundColor: 'black',
    },
    conatinerImage: {
        width: '100%',
        height: '100%',
        position: 'absolute',
        transform: [{scale: 1.19}],
    },
});