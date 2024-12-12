import React from "react";
import { useRouter } from "expo-router";
import { View, StyleSheet, TouchableOpacity } from "react-native";

import { BackArrow } from "@/assets/svg";
import TextComponent from "./TextComponents";
import { EditIcon } from "@/assets/svg";

interface Props{
    title: string,
    edit?: Boolean
};

export default function HeaderComponent({title, edit = false}: Props) {

    const router = useRouter();

    const handleBack = () => {
        router.back();
    }

    return (
        <View style={styles.header}>
            <TouchableOpacity onPress={handleBack} style={styles.iconStyle}>
                <BackArrow width={30.89} height={14.81} />
            </TouchableOpacity>

            <TextComponent text={title} size={22} color="#FFFFFF"/>

            {edit && 
                <TouchableOpacity style={styles.iconStyle}>
                    <EditIcon width={24} height={24} />
                </TouchableOpacity>
            }
            {!edit && 
                <View style={styles.placeholder} />
            }
        </View>
    )
};


const styles = StyleSheet.create({
    header: {
      flexDirection: 'row',
      alignItems: 'center',
      justifyContent: 'space-between',
      paddingHorizontal: 8,
    },
    iconStyle: {
      width: 31,
    },
    placeholder: {
        width: 31
    },
  });