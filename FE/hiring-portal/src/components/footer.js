import React from 'react'
import { Link } from 'react-router-dom'

function Footer() {
    return (
        <footer className="main-footer">
            <div className="float-right d-none d-sm-block">
                <b>Version</b> 1.0.0
            </div>
            <strong>Copyright &copy; 2023 <Link to="https://www.amartek.id/">Bumi Amartha Teknologi Mandiri</Link>.</strong> All rights reserved.
        </footer>
    )
}

export default Footer