import { height, width } from "@/constants/Dimensions";
import { LinearGradient } from "expo-linear-gradient";
import React from "react";
import { StyleSheet, View } from "react-native";

interface Props {
    children: React.ReactNode
};

export default function TopRightGradient({children}: Props) {

    return (
        <View style={styles.container}>
            <LinearGradient 
                colors={["black", "#4361EE3D", "#8C3FFF3D"]}
                locations={[0, 0.5, 1]}
                start={{x: 0.5, y: 0.5}}
                end={{x:1, y: 0}}
                style={styles.gradient}
            />
            {children}
        </View>
    )
};


const styles = StyleSheet.create({
	container: {
        flex: 1,
        backgroundColor: '#000',
    },
    gradient: {
		position: 'absolute',
		top: 0,
		right: 0,
		width: width,
		height: height
	},
});